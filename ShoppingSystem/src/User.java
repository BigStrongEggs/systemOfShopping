import java.util.Scanner;

public  class User {
    private String myPassWord;
    private int account;
    User (){}
    User (String myPassWord,int account){
        this.account=account;
        this.myPassWord=myPassWord;
    }
    public int getAccount(){
        return this.account;
    }
    public void setAccount(int account){
        this.account=account;
    }
    public String getMyPassWord(){
        return this.myPassWord;
    }
    public void setMyPassWord(String myPassWord){
        this.myPassWord=myPassWord;
    }
    public void modifyOwnPassWord(){
        while (true){
            System.out.println("请输入新的密码：");
            Scanner sc=new Scanner(System.in);
            String newPassWord= sc.next();
            if(PassWord.isPassWordOk(newPassWord)){
                this.setMyPassWord(newPassWord);
                System.out.println("密码修改成功！");
                break;
            }
            else{
                System.out.println("密码必须包含大小写字母、数字、标点符号且长度必须大于8,请重新输入");
            }
        }
    }

}
