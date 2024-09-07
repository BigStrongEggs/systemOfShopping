import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Manager extends User implements Login {
    Manager(){
        super("ynuinfo#777",10000);
    }
    //实现登录登出功能
    public boolean signIn(){
        int account1;
        try {
            System.out.println("请输入管理员账号");
            Scanner sc1=new Scanner(System.in);
            account1= sc1.nextInt();
        }catch (InputMismatchException exception){
            System.out.println("您输入的不是整型数据，请重新输入");
            return false;
        }
        System.out.println("请输入管理员密码");
        Scanner sc2=new Scanner(System.in);
        String password= sc2.next();
        try {
            if(DataBase.managerInfo.get(account1).equals(password)){
                System.out.println("登录成功");
                return true;
            }
            else{
                System.out.println("密码错误，请重新输入");
                return false;
            }
        }catch (NullPointerException exception){
            System.out.println("账号错误，请重新输入");
            return false;
        }
    }
    public boolean signUp(){
        return true;
    }
    //*********************************************实现密码管理功能***************************************************

    public static boolean modifyCustomersPassWord(Customer customer){
        String newPassWord=PassWord.generateRandomPassWord();
        if(newPassWord==null){
            return false;
        }
        DataBase.modifyCustomerPassWord(customer,newPassWord);
        System.out.println("密码修改成功！");
        return true;
    }
    public static String modifyCustomersPassWord(String userName,String email){
        Customer customer = null;
        for (Customer temp : DataBase.customerInfo) {
            if(temp.getUserName().equals(userName)&&temp.getEmail().equals(email)){
                customer=temp;
            }
        }
        String newPassWord=PassWord.generateRandomPassWord();
        if(newPassWord==null||customer==null){
            return null;
        }
        DataBase.modifyCustomerPassWord(customer,newPassWord);
        //System.out.println("密码修改成功！");
        return newPassWord;
    }
    public static String modifyCustomersPassWord(int customerID){
        Customer customer = null;
        for (Customer temp : DataBase.customerInfo) {
            if(temp.getCustomerId()==customerID){
                customer=temp;
            }
        }
        String newPassWord=PassWord.generateRandomPassWord();
        if(newPassWord==null||customer==null){
            return null;
        }
        DataBase.modifyCustomerPassWord(customer,newPassWord);
        System.out.println("密码修改成功！");
        return newPassWord;
    }
    //*****************************************实现客户管理功能**********************************************************
    //列出所有户的信息
    public static void printAllCustomerInfo(){
        if(DataBase.customerInfo==null){
            System.out.println("无用户信息");
            return;
        }
        System.out.println("-------------------------------------所有用户信息----------------------------------");
        System.out.printf("%8s%8s%6s%16s%13s%10s%16s\n","客户ID","用户名","用户级别","注册时间","累计消费总金额","手机号","邮箱");
        Iterator<Customer> iter=DataBase.customerInfo.iterator();
        while(iter.hasNext()) {
            Customer c = iter.next();
            System.out.printf("%8d%13s%6d%20s%18f%15d%16s\n", c.getCustomerId(), c.getUserName(), c.getLevel(), c.getTimeOfRegister(), c.getTotalSpent(), c.getPhoneNumber(), c.getEmail());
        }
    }
    //删除用户信息
    public static void deleteCustomerInfo(Customer customer){
        System.out.println("您确定要删除该用户的信息吗？（确定请输入：1   不确定请输入：2）");
        while(true){
            Scanner sc=new Scanner(System.in);
            int ans= sc.nextInt();
            if(ans==1){
                DataBase.customerInfo.remove(customer);
                System.out.println("删除成功");
                return;
            }
            else if(ans==2){
                System.out.println("已取消删除操作");
                return;
            }
            else{
                System.out.println("输入错误请重新输入：");
            }
        }
    }
    //查询特定用户的信息
    //根据用户id查找
    public static Customer searchForCustomerWithID(int Id){
        for (Customer ct : DataBase.customerInfo) {
            if (ct.getCustomerId() == Id) {
               return ct;
            }
        }
        return null;
    }
    public static boolean searchForCustomerInfo(int Id){
        for (Customer ct : DataBase.customerInfo) {
            if (ct.getCustomerId() == Id) {
                System.out.printf("---------------------------用户ID为%d的用户信息如下--------------------------\n", Id);
                System.out.printf("%8s%8s%6s%16s%13s%10s%16s\n", "客户ID", "用户名", "用户级别", "注册时间", "累计消费总金额", "手机号", "邮箱");
                System.out.printf("%8d%13s%6d%20s%18f%15d%16s\n", ct.getCustomerId(), ct.getUserName(), ct.getLevel(), ct.getTimeOfRegister(), ct.getTotalSpent(), ct.getPhoneNumber(), ct.getEmail());
                return true;
            }
        }
        return false;
    }
    //根据用户名查找
    public static int searchForCustomerInfo(String userName){
        int count=0;
        System.out.printf("---------------------------用户名为%s的用户信息如下--------------------------\n", userName);
        System.out.printf("%8s%8s%6s%16s%13s%10s%16s\n", "客户ID", "用户名", "用户级别", "注册时间", "累计消费总金额", "手机号", "邮箱");
        for (Customer ct : DataBase.customerInfo) {
            if (ct.getUserName().equals(userName)) {
                System.out.printf("%8d%13s%6d%20s%18f%15d%16s\n", ct.getCustomerId(), ct.getUserName(), ct.getLevel(), ct.getTimeOfRegister(), ct.getTotalSpent(), ct.getPhoneNumber(), ct.getEmail());
                count++;
            }
        }
        return count;
    }
    //******************************************实现商品管理************************************************************
    //列出所有商品信息
    public static void printAllGoodsInfo(List<Goods> list){
        if(list.isEmpty()){
            System.out.println("无商品信息");
            return;
        }
        System.out.println("-------------------------------------所有商品信息如下----------------------------------------");
        System.out.printf("%10s%10s%15s%15s%13s%12s%12s%10s\n","商品编号","商品名称","生产厂家","生产日期","商品型号","进货价","零售价格","商品数量");
        for (Goods c : list) {
            System.out.printf("%10d%13s%15s%21s%15s%16f%18f%10d\n", c.getGoodsID(), c.getGoodsName(), c.getManufacturer(), c.getProducedTime(), c.getModelNumber(), c.getPurchasePrice(), c.getSellingPrice(), c.getQuantity());
        }
    }
    //*****************************************添加商品信息
    public static void addGoods(int goodsID,String goodsName,String manufacturer,String producedTime,String modelNumber,double purchasePrice,double sellingPrice,int quantity){
        Goods goods=new Goods();
        goods.setGoodsID(goodsID);
        goods.setGoodsName(goodsName);
        goods.setManufacturer(manufacturer);
        goods.setModelNumber(modelNumber);
        goods.setProducedTime(producedTime);
        goods.setPurchasePrice(purchasePrice);
        goods.setSellingPrice(sellingPrice);
        goods.setQuantity(quantity);
        DataBase.goodsInfo.add(goods);
    }
    public void addGoods(Goods goods){
        DataBase.goodsInfo.add(goods);
    }
    //*******************************************修改商品信息
    //*****查找对应的商品
    public static Goods searchSpecialGoods(int goodsID,Integer count){
        int i=0;
        for (Goods temp : DataBase.goodsInfo) {
            i++;
            if (temp.getGoodsID() == goodsID) {
                count=i;
                return temp;
            }
        }
        return null;
    }
    //*******修改商品的各个信息
    public static boolean modifyGoodsID(int preGoodsID, int newGoodsID){
        Integer count=0;
        Goods goods=searchSpecialGoods(preGoodsID,count);
        if (goods != null) {
            goods.setGoodsID(newGoodsID);
            DataBase.goodsInfo.set(count,goods);
            return true;
        }
        return false;
    }
    public static boolean modifyGoodsName(int goodsID,String newName){
        Integer count=0;
        Goods goods=searchSpecialGoods(goodsID,count);
        if (goods != null) {
            goods.setGoodsName(newName);
            DataBase.goodsInfo.set(count,goods);
            return true;
        }
        return false;
    }
    public static boolean modifyGoodsManufacturer(int goodsID,String newManufacturer){
        Integer count=0;
        Goods goods=searchSpecialGoods(goodsID,count);
        if (goods != null) {
            goods.setManufacturer(newManufacturer);
            DataBase.goodsInfo.set(count,goods);
        }
        return false;
    }
    public static boolean modifyGoodsTime(int goodsID,String newTime){
        Integer count=0;
        Goods goods=searchSpecialGoods(goodsID,count);
        if (goods != null) {
            goods.setProducedTime(newTime);
            DataBase.goodsInfo.set(count,goods);
            return true;
        }
        return false;
    }
    public static boolean modifyGoodsModelNumber(int goodsID,String newModelNumber){
        Integer count=0;
        Goods goods=searchSpecialGoods(goodsID,count);
        if (goods != null) {
            goods.setModelNumber(newModelNumber);
            DataBase.goodsInfo.set(count,goods);
            return true;
        }
        return false;
    }
    public static boolean modifyGoodsPurchasePrice(int goodsID, double newPurchasePrice){
        Integer count=0;
        Goods goods=searchSpecialGoods(goodsID,count);
        if (goods != null) {
            goods.setPurchasePrice(newPurchasePrice);
            DataBase.goodsInfo.set(count,goods);
            return true;
        }
        return false;
    }
    public static boolean modifyGoodsSellingPrice(int goodsID,double newSellingPrice){
        Integer count=0;
        Goods goods=searchSpecialGoods(goodsID,count);
        if (goods != null) {
            goods.setSellingPrice(newSellingPrice);
            DataBase.goodsInfo.set(count,goods);
            return true;
        }
        return false;
    }
    public static boolean modifyGoodsQuantity(int goodsID,int newQuantity){
        Integer count =0;
        Goods goods=searchSpecialGoods(goodsID,count);
        if (goods != null) {
            goods.setQuantity(newQuantity);
            DataBase.goodsInfo.set(count,goods);
            return true;
        }
        return false;
    }
    public static void modifyGoodsInfo(Goods pregoods,Goods newgoods){
        Integer count=0;
        searchSpecialGoods(pregoods.getGoodsID(),count);
        DataBase.goodsInfo.set(count,newgoods);
    }
    //*********************删除商品的信息
    public static void deleteGoodsInfo(Goods goods){
        int ans;
        while(true){
            System.out.println("您确定要删除该商品的信息吗？（确定请输入：1   不确定请输入2）");
            try {
                Scanner sc=new Scanner(System.in);
                ans= sc.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(ans==1){
                DataBase.goodsInfo.remove(goods);
                System.out.println("已删除该商品");
            }
            else if(ans==2){
                System.out.println("已取消删除操作");
            }
            else{
                System.out.println("输入错误请重新输入：");
                continue;
            }
            break;
        }
    }
    //*****************************查询商品信息
    public static boolean isGoodsNameOk(String goodsName,Goods temp){
        if (temp.getGoodsName() .equals(goodsName)) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean isManufacturerOk(String manufacturer,Goods temp){
        if (temp.getManufacturer() .equals(manufacturer)) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean isSellingPriceOk(double sellingPrice, @NotNull Goods temp){
        if (temp.getSellingPrice()<=sellingPrice) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void searchGoodsInfoWithName(String goodsName){
        List<Goods> list=new LinkedList<>();
        for (Goods temp : DataBase.goodsInfo) {
            if(isGoodsNameOk(goodsName,temp)){
                list.add(temp);
            }
        }
        printAllGoodsInfo(list);
    }
    public static void searchGoodsInfoWithManufacturer(String manufacturer){
        List<Goods> list=new LinkedList<>();
        for (Goods temp : DataBase.goodsInfo) {
            if(isManufacturerOk(manufacturer,temp)){
                list.add(temp);
            }
        }
        printAllGoodsInfo(list);
    }
    public static void searchGoodsInfoWithSellingPrice(double sellingPrice){
        LinkedList<Goods> list=new LinkedList<>();
        for (Goods temp : DataBase.goodsInfo) {
            if(isSellingPriceOk(sellingPrice,temp)){
                list.add(temp);
            }
        }
        printAllGoodsInfo(list);
    }
    //以两个元素为条件进行查找
    public static void searchGoodsInfoWithNameAndMf(String name,String manufacturer){
        LinkedList<Goods> list=new LinkedList<>();
        for (Goods temp : DataBase.goodsInfo) {
            if(isGoodsNameOk(name,temp)&& isManufacturerOk(manufacturer,temp)){
                list.add(temp);
            }
        }
        printAllGoodsInfo(list);
    }
    public static void searchGoodsInfoWithNameAndSP(String name,double sellingPrice){
        List<Goods> list=new LinkedList<>();
        for (Goods temp : DataBase.goodsInfo) {
            if(isGoodsNameOk(name,temp)&& isSellingPriceOk(sellingPrice,temp)){
                list.add(temp);
            }
        }
        printAllGoodsInfo(list);
    }
    public static void searchGoodsInfoWithSPAndMf(double sellingPrice,String manufacturer){
        LinkedList<Goods> list=new LinkedList<>();
        for (Goods temp : DataBase.goodsInfo) {
            if(isSellingPriceOk(sellingPrice,temp)&& isManufacturerOk(manufacturer,temp)){
                list.add(temp);
            }
        }
        printAllGoodsInfo(list);
    }
    //以三个元素为条件进行查找
    public static void searchGoodsInfoWithThreeActor(double sellingPrice,String name,String manufacturer){
        LinkedList<Goods> list=new LinkedList<>();
        for (Goods temp : DataBase.goodsInfo) {
            if(isGoodsNameOk(name,temp)&& isManufacturerOk(manufacturer,temp)&& isSellingPriceOk(sellingPrice,temp)){
                list.add(temp);
            }
        }
        printAllGoodsInfo(list);
    }

}
