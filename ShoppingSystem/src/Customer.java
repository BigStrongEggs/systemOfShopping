import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Customer extends User implements Login,Register{
    private ShoppingCart myShoppingCart=new ShoppingCart();
    private int customerId;
    private String userName;
    private int level;    //1为金牌客户  2为银牌客户  3为铜牌客户
    private String timeOfRegister;
    private double totalSpent;
    private long phoneNumber;
    private String email;
    private boolean isLocked;
    Customer(){}
    Customer(int customerId,String userName,int level,String timeOfRegister,long phoneNumber,String email,String password,int account){
        super(password,account);
        this.customerId=customerId;
        this.userName=userName;
        this.level=level;
        this.totalSpent=0;
        this.timeOfRegister=timeOfRegister;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.isLocked=false;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public ShoppingCart getMyShoppingCart() {
        return myShoppingCart;
    }

    public void setIsLocked(boolean locked) {
        this.isLocked = locked;
    }

    public int getCustomerId() {
        return customerId;
    }
    public double getTotalSpent() {
        return totalSpent;
    }
    public int getLevel() {
        return level;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTimeOfRegister() {
        return timeOfRegister;
    }

    public String getUserName() {
        return userName;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTimeOfRegister(String timeOfRegister) {
        this.timeOfRegister = timeOfRegister;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMyShoppingCart(ShoppingCart myShoppingCart) {
        this.myShoppingCart = myShoppingCart;
    }
    public void setMyShoppingCart(LinkedList<GoodsInCart> shoppingCartInfo) {
        this.myShoppingCart.shoppingCartInfo=shoppingCartInfo;
    }
    public void setMyHistoryShoppingCart(LinkedList<GoodsInCart> historyInfo){
        this.myShoppingCart.historyInfo=historyInfo;
    }
    //**********************************实现登录登出功能

    @Override
    public boolean signIn() {
        System.out.println("请输入用户账号：");
        Scanner sc1=new Scanner(System.in);
        int account1= sc1.nextInt();
        for (Customer temp : DataBase.customerInfo) {
            if (temp.getAccount() == account1) {
                this.setCustomerId(temp.getCustomerId());
                if(temp.isLocked){
                    System.out.println("您已连续5次输入密码错误，该账号已锁定");
                    return false;
                }
                int count = 0;
                while (count < 5) {
                    System.out.println("请输入用户密码");
                    Scanner sc2 = new Scanner(System.in);
                    String password = sc2.next();
                    if (temp.getMyPassWord().equals(password)) {
                        System.out.println("登录成功");
                        return true;
                    }
                    else{
                        System.out.println("密码错误，请重新输入");
                        count++;
                    }
                }
                this.setIsLocked(true);
                System.out.println("您已连续5次输入密码错误，该账号已锁定");
                return false;
            }
        }
        return false;//账号输入错误
    }

    @Override
    public boolean signUp() {

        return true;
    }
    //**********************************实现注册功能********************************************

    @Override
    public boolean isUserNameOk(String userName) {
        if(userName.length()>=5){
            return true;
        }
        return false;
    }
    @Override
    public Customer register() {
        while(true){
            System.out.println("------------------------欢迎来到用户注册界面-------------------------------");
            System.out.println("用户名长度不少于5个字符，密码必须包含大小写字母、数字、标点符号且长度大于8");
            System.out.println("请输入用户名:");
            System.out.print("   ");
            Scanner sc1=new Scanner(System.in);
            String userName=sc1.next();
            System.out.println("请输入密码:");
            System.out.print("   ");
            Scanner sc2=new Scanner(System.in);
            String passWord=sc2.next();
            if(this.isUserNameOk(userName)&&PassWord.isPassWordOk(passWord)){
                LocalDate now=LocalDate.now();
                String time=now.toString();
                System.out.println("请输入邮箱：");
                Scanner sc3=new Scanner(System.in);
                String email=sc3.next();
                System.out.println("请输入电话号码：");
                Scanner sc4=new Scanner(System.in);
                long phoneNumbers=Long.parseLong(sc4.next());
                int ID=0,account1=0;
                if(DataBase.customerInfo.isEmpty()){
                    account1=2024718;
                    ID=1;
                }
                else{
                    ID=DataBase.customerInfo.getLast().customerId+1;
                    account1=DataBase.customerInfo.getLast().getAccount()+1;
                }
                Customer customer=new Customer(ID,userName,3,time,phoneNumbers,email,passWord,account1);
                DataBase.addCustomerInfo(customer);
                return customer;
            }
            else if(this.isUserNameOk(userName)&&!PassWord.isPassWordOk(passWord)){
                System.out.println("输入密码有误，请重新输入密码");
            }
            else if (PassWord.isPassWordOk(passWord)&&!this.isUserNameOk(userName)) {
                System.out.println("输入用户名有误，请重新输入");
            }
            else{
                System.out.println("输入密码和用户名均有误，请重新输入");
            }
        }
    }
    //*******************************密码管理*********************************
    //忘记密码
    public boolean forgetPassWord(){
        System.out.println("请输入用户名:");
        System.out.print("   ");
        Scanner sc1=new Scanner(System.in);
        String userName=sc1.next();
        System.out.println("请输入注册邮箱地址:");
        System.out.print("   ");
        Scanner sc2=new Scanner(System.in);
        String email=sc2.next();
        String newPassWord=Manager.modifyCustomersPassWord(userName,email);
        if(newPassWord==null){
            System.out.println("输入的用户名或邮箱地址有误，请重新输入");
            return false;
        }
        else{
            System.out.println("重置密码成功，您的新密码为： "+newPassWord);
            return true;
        }
    }
    //*************************************实现购物功能**********************************************

}
