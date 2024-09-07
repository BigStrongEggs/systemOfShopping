import java.security.PublicKey;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class ShoppingSystem {
    public void initialization(){
        int isFirstInManager=0;
        while(true){
            System.out.println("***********************************欢迎来到购物管理系统****************************************");
            System.out.println("请选择您的身份：");
            System.out.println("      1.管理员");
            System.out.println("      2.客户");
            System.out.println("      3.退出系统，结束程序");
            int identity=0;
            try{
                Scanner scOfIdentity=new Scanner(System.in);
                identity=scOfIdentity.nextInt();
            }catch (InputMismatchException exception) {
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(identity==1){
               int i=this.managerSystem(isFirstInManager);
               isFirstInManager+=i;
            }
            else if(identity==2){
                this.customerSystem();
            }
            else if(identity==3){
                return;
            }
            else{
                System.out.println("输入错误请重新输入");
            }
        }
    }
    //**************************************************************管理员系统*************************************************************
    public int managerSystem(int i){
        int flag=0;
        Manager manager=new Manager();
        if(i==0){
            DataBase.addManagerInfo(manager.getAccount(),manager.getMyPassWord());
            flag++;
        }
        //登录界面
        while(true){
            if (manager.signIn()) break;
        }
        //******显示管理员权限
        while(true){
            System.out.println("***********************欢迎来到管理员界面********************************");
            System.out.println("      1.密码管理");
            System.out.println("      2.客户管理");
            System.out.println("      3.商品管理");
            System.out.println("      4.退出登录");
            int managerChoice=0;
            try{
                Scanner scOfManagerChoice=new Scanner(System.in);
                managerChoice=scOfManagerChoice.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(managerChoice==1){
                this.manageOfPW(manager);
            }
            else if(managerChoice==2){
                this.manageOfCustomer();
            }
            else if(managerChoice==3){
                this.manageOfGoods();
            }
            else if(managerChoice==4){
                return flag;
            }
            else{
                System.out.println("您输入的数字无意义，请重新输入");
            }
        }
    }
    //******显示管理员权限
    //*******************************密码管理********************************
    public boolean manageOfPW(Manager manager){
        while(true){
            System.out.println("*************密码管理************");
            System.out.println("     1.修改管理员密码");
            System.out.println("     2.修改指定用户的密码");
            System.out.println("     3.返回上一级");
            int managerPW;
            try{
                Scanner sc=new Scanner(System.in);
                managerPW=sc.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(managerPW==1){
                manager.modifyOwnPassWord();
                DataBase.addManagerInfo(manager.getAccount(),manager.getMyPassWord());
            }
            else if(managerPW==2){
                while(true){
                    System.out.println("*****修改指定用户的密码****");
                    System.out.print("   请输入用户ID： ");
                    int ID;
                    try{
                        Scanner sc1=new Scanner(System.in);
                        ID= sc1.nextInt();
                    }catch (InputMismatchException exception){
                        System.out.println("用户ID只包含整数，您输入的不是整型数据，请重新输入");
                        continue;
                    }
                    String newPW= Manager.modifyCustomersPassWord(ID);
                    if(newPW==null){
                        System.out.println("您输入的用户ID有误，请重新输入");
                    }
                    else{
                        break;
                    }
                }
            }
            else if(managerPW==3){
                return true;
            }
            else{
                System.out.println("输入有误请重新输入");
            }
        }
    }
    //****************************密码管理********************************


    //****************************客户管理********************************
    public boolean manageOfCustomer(){
        while (true){
            System.out.println("*************客户管理************");
            System.out.println("     1.列出所有客户信息");
            System.out.println("     2.删除客户信息");
            System.out.println("     3.查询客户信息");
            System.out.println("     4.返回上一级");
            int managerC;
            try{
                Scanner sc1=new Scanner(System.in);
                managerC=sc1.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(managerC==1){
                Manager.printAllCustomerInfo();
            }
            else if(managerC==2){
                this.deleteCustomerInfo();
            }
            else if(managerC==3){
                boolean b = this.searchCustomerInfo();
            }
            else if(managerC==4){
                return true;
            }
            else{
                System.out.println("您输入的数字无意义，请重新输入");
            }
        }
    }
    public void deleteCustomerInfo(){
        while(true){
            System.out.println("*****删除客户信息******");
            System.out.print("   请输入用户ID：");
            int ID;
            try{
                Scanner sc2=new Scanner(System.in);
                ID= sc2.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("用户ID只包含整数，您输入的不是整型数据，请重新输入");
                continue;
            }
            Customer customer=Manager.searchForCustomerWithID(ID);
            if(customer==null){
                System.out.println("您输入的用户ID有误，请重新输入");
                continue;
            }
            Manager.deleteCustomerInfo(customer);
            break;
        }
    }
    public boolean searchCustomerInfo(){
        while(true){
            System.out.println("********查询客户信息********");
            System.out.println("     1.根据客户ID查询客户信息");
            System.out.println("     2.根据用户名查询客户信息");
            System.out.println("     3.返回上一级");
            System.out.print("请输入您要执行的操作：");
            int choice;
            try {
                Scanner sc3=new Scanner(System.in);
                choice=sc3.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(choice==1){
                while(true){
                    System.out.println("*****根据客户ID查询客户信息******");
                    System.out.print("   请输入用户ID：");
                    int ID;
                    try {
                        Scanner sc2=new Scanner(System.in);
                        ID= sc2.nextInt();
                    }catch (InputMismatchException exception){
                        System.out.println("用户ID只包含整数，您输入的不是整型数据，请重新输入");
                        continue;
                    }
                    if(!Manager.searchForCustomerInfo(ID)){
                        System.out.println("您输入的用户ID有误，请重新输入");
                        continue;
                    }
                    break;
                }
            }
            if(choice==2){
                while(true){
                    System.out.println("*****根据用户名查询客户信息******");
                    System.out.print("   请输入用户名：");
                    Scanner sc2=new Scanner(System.in);
                    String name= sc2.next();
                    if(Manager.searchForCustomerInfo(name)==0){
                        System.out.println("您输入的用户名有误，请重新输入");
                        continue;
                    }
                    break;
                }
            }
            if(choice==3){
                return true;
            }
        }
    }
    //**************************客户管理***************************


    //*************************商品管理****************************
    public void manageOfGoods(){
        while (true){
            System.out.println("*************商品管理************");
            System.out.println("     1.列出所有商品信息");
            System.out.println("     2.添加商品信息");
            System.out.println("     3.修改商品信息");
            System.out.println("     4.删除商品信息");
            System.out.println("     5.查询商品信息");
            System.out.println("     6.返回上一级");
            int managerG;
            try {
                Scanner sc1=new Scanner(System.in);
                managerG=sc1.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(managerG==1){
                Manager.printAllGoodsInfo(DataBase.goodsInfo);
            }
            else if(managerG==2){
                this.addGoods();
            }
            else if(managerG==3){
                int preID;
                while (true) {
                    try {
                        System.out.print("输入待修改的商品编号：");
                        Scanner sc1 = new Scanner(System.in);
                        preID = sc1.nextInt();
                        boolean isPreIDok=false;
                        for(Goods temp: DataBase.goodsInfo){
                            if (temp.getGoodsID() == preID) {
                                isPreIDok = true;
                                break;
                            }
                        }
                        if(!isPreIDok){
                            System.out.println("商品编号为："+preID+"的商品不存在，请重新输入  (输入'1'退出该功能，输入'2'继续该功能)");
                            Scanner sc2 = new Scanner(System.in);
                            int isGoing = sc2.nextInt();
                            if(isGoing==1){
                                break;
                            }
                            continue;
                        }
                        break;
                    }catch (InputMismatchException exception){

                        System.out.println("商品编号只包含整数，您输入的不是整型数据，请重新输入");
                        continue;
                    }
                }
                this.deleteGoods(preID);
            }
            else if(managerG==4){
                this.removeGoods();
            }
            else if(managerG==5){
                this.searchGoods();
            }
            else if(managerG==6){
                return;
            }
            else{
                System.out.println("输入有误请重新输入");
            }
        }
    }
    public void addGoods(){
        System.out.println("****添加商品信息****");
        int goodsID;
        if(DataBase.goodsInfo.isEmpty()){
            goodsID=1;
        }
        else {
            goodsID=DataBase.goodsInfo.getLast().getGoodsID()+1;
        }
        System.out.print("请输入商品名称：");
        Scanner sc1=new Scanner(System.in);
        String goodsName=sc1.next();
        System.out.print("请输入商品制造商：");
        Scanner sc2=new Scanner(System.in);
        String goodsManufacturer=sc2.next();
        System.out.print("请输入商品制造时间：");
        Scanner sc3=new Scanner(System.in);
        String goodsTime=sc3.next();
        System.out.print("请输入商品型号：");
        Scanner sc4=new Scanner(System.in);
        String goodsModel=sc4.next();
        double purchase;
        while (true){
            try {
                System.out.print("请输入商品进价：");
                Scanner sc5=new Scanner(System.in);
                purchase=sc5.nextDouble();
                break;
            }catch (InputMismatchException exception){
                System.out.println("商品进价为小数，您输入的不是浮点型数据，请重新输入");
                continue;
            }
        }
        double selling;
        while (true){
            try {
                System.out.print("请输入商品售价：");
                Scanner sc6=new Scanner(System.in);
                selling=sc6.nextDouble();
                break;
            }catch (InputMismatchException exception){
                System.out.println("商品售价为小数，您输入的不是浮点型数据，请重新输入");
                continue;
            }
        }
        int goodsNum;
        while (true){
            try {
                System.out.print("请输入商品数量：");
                Scanner sc7=new Scanner(System.in);
                goodsNum=sc7.nextInt();
                break;
            }catch (InputMismatchException exception){
                System.out.println("商品数量为整数，您输入的不是整型数据，请重新输入");
                continue;
            }
        }
        Manager.addGoods(goodsID,goodsName,goodsManufacturer,goodsTime,goodsModel,purchase,selling,goodsNum);
        System.out.println("添加成功!");
    }
    public void deleteGoods(int preID){
        while (true){
            System.out.println("****修改商品信息****");
            System.out.println("     1.修改商品编号");
            System.out.println("     2.修改商品名称");
            System.out.println("     3.修改商品制造商");
            System.out.println("     4.修改商品制造商日期");
            System.out.println("     5.修改商品型号");
            System.out.println("     6.修改商品进价");
            System.out.println("     7.修改商品售价");
            System.out.println("     8.修改商品数量");
            System.out.println("     9.返回上一级");
            System.out.println("请输入您要执行的操作");
            int choice;
            try {
                Scanner sc=new Scanner(System.in);
                choice=sc.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    while (true) {
                        int newID;
                        try {
                            System.out.print("输入新商品编号：");
                            Scanner sc2 = new Scanner(System.in);
                            newID = sc2.nextInt();
                            Manager.modifyGoodsID(preID,newID);
                            break;
                        }catch (InputMismatchException exception){
                            System.out.println("商品编号为只包含整数，您输入的不是整型数据，请重新输入");
                        }
                    }
                }
                case 2 -> {
                    System.out.print("输入新商品名称：");
                    Scanner sc2 = new Scanner(System.in);
                    String newName = sc2.next();
                    Manager.modifyGoodsName(preID,newName);
                }
                case 3 -> {
                     System.out.print("输入新商品制造商：");
                     Scanner sc2 = new Scanner(System.in);
                     String newManufacturer = sc2.next();
                     Manager.modifyGoodsManufacturer(preID,newManufacturer);
                }
                case 4 -> {
                     System.out.print("输入新商品制造日期：");
                     Scanner sc2 = new Scanner(System.in);
                     String newTime = sc2.next();
                     Manager.modifyGoodsTime(preID,newTime);
                }
                case 5 -> {
                     System.out.print("输入新商品型号：");
                     Scanner sc2 = new Scanner(System.in);
                     String newModel = sc2.next();
                     Manager.modifyGoodsModelNumber(preID,newModel);
                }
                case 6 -> {
                    while (true) {
                        try {
                            System.out.print("输入新的商品进价：");
                            Scanner sc2 = new Scanner(System.in);
                            double newPurchase = sc2.nextDouble();
                            Manager.modifyGoodsPurchasePrice(preID,newPurchase);
                            break;
                        }catch (InputMismatchException exception){
                            System.out.println("商品进价为小数，您输入的不是浮点型数据，请重新输入");
                        }
                    }
                }
                case 7 -> {
                    while (true) {
                        try {
                            System.out.print("输入新的商品售价：");
                            Scanner sc2 = new Scanner(System.in);
                            double newSelling = sc2.nextDouble();
                            Manager.modifyGoodsSellingPrice(preID, newSelling);
                            break;
                        }catch (InputMismatchException exception){
                            System.out.println("商品售价为小数，您输入的不是浮点型数据，请重新输入");
                        }
                    }
                }
                case 8 -> {
                    while (true) {
                        try {
                            System.out.print("输入新的商品数量：");
                            Scanner sc2 = new Scanner(System.in);
                            int newNum = sc2.nextInt();
                            Manager.modifyGoodsQuantity(preID, newNum);
                            break;
                        }catch (InputMismatchException exception){
                            System.out.println("商品数量为整数，您输入的不是整型据，请重新输入");
                        }
                    }
                }
                case 9 -> {
                    return;
                }
                default -> System.out.println("输入错误请重新输入");
            }
        }
    }
    public void removeGoods(){
        System.out.println("****删除商品信息****");
        while (true){
            int ID;
            try {
                System.out.print("输入待删除商品的编号：");
                Scanner sc=new Scanner(System.in);
                ID=sc.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("商品编号只包含整数，您输入的不是整型数据，请重新输入");
                continue;
            }
            Goods goods=Manager.searchSpecialGoods(ID,0);
            if(goods==null){
                continue;
            }
            Manager.deleteGoodsInfo(goods);
            break;
        }
    }
    public void searchGoods(){
        while(true){
            System.out.println("****查询商品信息****");
            System.out.println("    1.根据商品名称查询商品信息");
            System.out.println("    2.根据制造商查询商品信息");
            System.out.println("    3.根据零售价格查询商品信息");
            System.out.println("    4.根据商品名称和制造商查询商品信息");
            System.out.println("    5.根据商品名称和零售价查询商品信息");
            System.out.println("    6.根据制造商和零售价查询商品信息");
            System.out.println("    7.根据商品名称、制造商和零售价查询商品信息");
            System.out.println("    8.返回上一级");
            Scanner sc=new Scanner(System.in);
            int choice=sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("输入商品名称：");
                    Scanner sc1 = new Scanner(System.in);
                    String name = sc1.next();
                    Manager.searchGoodsInfoWithName(name);
                }
                case 2 -> {
                    System.out.print("输入商品制造商：");
                    Scanner sc2 = new Scanner(System.in);
                    String manufacturer = sc2.next();
                    Manager.searchGoodsInfoWithManufacturer(manufacturer);
                }
                case 3 -> {
                    while (true){
                        try {
                            System.out.print("输入商品售价：");
                            Scanner sc3 = new Scanner(System.in);
                            double selling = sc3.nextDouble();
                            Manager.searchGoodsInfoWithSellingPrice(selling);
                            break;
                        }catch (InputMismatchException exception){
                            System.out.println("商品售价为小数，您输入的不是浮点型数据，请重新输入");
                        }
                    }
                }
                case 4 -> {
                    System.out.print("输入商品名称：");
                    Scanner sc1 = new Scanner(System.in);
                    String name = sc1.next();
                    System.out.print("输入商品制造商：");
                    Scanner sc2 = new Scanner(System.in);
                    String manufacturer = sc2.next();
                    Manager.searchGoodsInfoWithNameAndMf(name,manufacturer);
                }
                case 5 -> {
                    while (true){
                        try {
                            System.out.print("输入商品名称：");
                            Scanner sc1 = new Scanner(System.in);
                            String name = sc1.next();
                            System.out.print("输入商品售价：");
                            Scanner sc2 = new Scanner(System.in);
                            double selling = sc2.nextDouble();
                            Manager.searchGoodsInfoWithNameAndSP(name,selling);
                            break;
                        }catch (InputMismatchException exception){
                            System.out.println("商品售价为小数，您输入的不是浮点型数据，请重新输入");
                        }
                    }
                }
                case 6 -> {
                    while (true) {
                        try {
                            System.out.print("输入商品售价：");
                            Scanner sc1 = new Scanner(System.in);
                            double selling = sc1.nextDouble();
                            System.out.print("输入商品制造商：");
                            Scanner sc2 = new Scanner(System.in);
                            String manufacturer = sc2.next();
                            Manager.searchGoodsInfoWithSPAndMf(selling,manufacturer);
                            break;
                        } catch (InputMismatchException exception){
                            System.out.println("商品售价为小数，您输入的不是浮点型数据，请重新输入");
                        }
                    }
                }
                case 7 -> {
                    while (true){
                        try {
                            System.out.print("输入商品名称: ");
                            Scanner sc1 = new Scanner(System.in);
                            String name = sc1.next();
                            System.out.print("输入商品制造商：");
                            Scanner sc2 = new Scanner(System.in);
                            String manufacturer = sc2.next();
                            System.out.print("输入商品售价：");
                            Scanner sc3 = new Scanner(System.in);
                            double selling = sc3.nextDouble();
                            Manager.searchGoodsInfoWithThreeActor(selling,name,manufacturer);
                            break;
                        }catch (InputMismatchException exception){
                            System.out.println("商品售价为小数，您输入的不是浮点型数据，请重新输入");
                        }
                    }
                }
                case 8 -> {
                    return;
                }
                default -> {
                    System.out.println("输入错误请重新输入");
                }
            }
        }
    }
    //*************************商品管理****************************

    //**************************************************************管理员系统*************************************************************


    //**************************************************************客户系统*************************************************************
    public void customerSystem(){
        Customer customer=new Customer();
        int isRegistered;
        while (true){
            System.out.println("是否注册过账号（若已有账号请输入 1 登录，若没有账号请输入 2 注册账号）");
            try {
                Scanner sc=new Scanner(System.in);
                isRegistered= sc.nextInt();
                break;
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
            }
        }
        if(isRegistered==1){
            while(true){
                boolean isLogined=customer.signIn();
                if(isLogined){
                    for(Customer temp: DataBase.customerInfo){
                        if(temp.getCustomerId()==customer.getCustomerId()){
                            customer=temp;
                        }
                    }
                    break;
                }
            }
        }
        else if(isRegistered==2){
            customer=customer.register();
            System.out.println("注册成功");
            System.out.println("您的用户账号为: "+customer.getAccount());
        }
        while(true){
            System.out.println("***********************欢迎来到用户界面********************************");
            System.out.println("      1.密码管理");
            System.out.println("      2.购物");
            System.out.println("      3.退出登录");
            int choice;
            try {
                Scanner sc1=new Scanner(System.in);
                choice=sc1.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(choice==1){
                this.customerOfPw(customer);
            }
            else if(choice==2){
                this.customerOfShopping(customer);
            }
            else if(choice==3){
                return;
            }
            else{
                System.out.println("输入错误请重新输入");
            }
        }
    }
    public void customerOfPw(Customer customer){
        while (true){
            System.out.println("***************密码管理**************");
            System.out.println("      1.修改密码");
            System.out.println("      2.忘记密码");
            System.out.println("      3.返回上一级");
            int choice;
            try {
                Scanner sc1=new Scanner(System.in);
                choice=sc1.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if(choice==1){
                customer.modifyOwnPassWord();
                DataBase.modifyCustomerPassWord(customer, customer.getMyPassWord());
            }
            else if(choice==2){
                while (true){
                    boolean isReseted=customer.forgetPassWord();
                    if(isReseted){
                        break;
                    }
                }
            }
            else if(choice==3){
                return;
            }
            else{
                System.out.println("输入错误请重新输入");
            }
        }

    }
    public void customerOfShopping(Customer customer){
        while(true){
            System.out.println("***************购物**************");
            System.out.println("      1.查看所有商品");
            System.out.println("      2.添加商品到购物车");
            System.out.println("      3.将商品从购物车中移除");
            System.out.println("      4.修改购物车中的商品");
            System.out.println("      5.结账");
            System.out.println("      6.查看购物历史");
            System.out.println("      7.查看购物车中的商品");
            System.out.println("      8.返回上一级");
            int choice;
            try {
                Scanner sc1=new Scanner(System.in);
                choice=sc1.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    Manager.printAllGoodsInfo(DataBase.goodsInfo);
                }
                case 2 -> {
                    this.addGoodsToCart(customer);
                }
                case 3 -> {
                    this.removeGoodsFromCart(customer);
                }
                case 4 -> {
                    this.modifyGoodsFromCart(customer);
                }
                case 5 -> {
                    this.customerOfPaying(customer);
                }
                case 6 -> {
                    this.searchForHistory(customer);
                }
                case 7 -> {
                    customer.getMyShoppingCart().printInfoOfShoppingCart();
                }
                case 8 -> {
                    return;
                }
                default -> {
                    System.out.println("输入有误请重新输入");
                }
            }
        }
    }
    public void addGoodsToCart(Customer customer){
        while (true){
            System.out.println("*******添加商品到购物车******");
            int ID,nums;
            try {
                System.out.println("请输入商品编号");
                Scanner sc1=new Scanner(System.in);
                ID=sc1.nextInt();
                System.out.println("请输入您要购买的商品数目：");
                Scanner sc2=new Scanner(System.in);
                nums=sc2.nextInt();
                if(customer.getMyShoppingCart().addGoodsToCart(ID, nums).isEmpty()){
                    continue;
                }
                //customer.setMyShoppingCart(customer.getMyShoppingCart().addGoodsToCart(ID,nums));
                DataBase.updateCustomersCart(customer);
                break;
            }catch (InputMismatchException exception){
                System.out.println("商品编号和商品数目都是整数，您输入的不是整型数据，请重新输入");
                continue;
            }catch (NullPointerException exception){
                System.out.println("输入数据有误，添加失败请重新输入");
            }finally {
                System.out.println("输入1退回到上一界面，输入2继续该功能");
                Scanner sc=new Scanner(System.in);
                int choice=Integer.parseInt(sc.next());
                if(choice==1){
                    return;
                }
            }
        }
    }
    public void removeGoodsFromCart(Customer customer){
        while (true) {
            System.out.println("*******将商品从购物车中移除*******");
            System.out.println("    1.移除指定商品");
            System.out.println("    2.移除所有商品");
            System.out.println("    3.返回上一界面");
            int choice;
            try {
                Scanner sc1=new Scanner(System.in);
                choice=sc1.nextInt();
            }catch (InputMismatchException exception){
                System.out.println("您输入的不是整型数据，请重新输入");
                continue;
            }
            if (choice == 1) {
                while (true) {
                    int ID;
                    try {
                        System.out.println("请输入商品编号");
                        Scanner sc1 = new Scanner(System.in);
                        ID = sc1.nextInt();
                        LinkedList<GoodsInCart> temp=customer.getMyShoppingCart().removeGoodsFromCart(ID);
                        if (temp==null) {
                            System.out.println("商品编号错误请重新输入");
                            continue;
                        }
                        customer.setMyShoppingCart(temp);
                        DataBase.updateCustomersCart(customer);
                        break;
                    }catch (InputMismatchException exception){
                        System.out.println("您输入的不是整型数据，请重新输入");
                    }finally {
                        System.out.println("输入1退回到上一界面，输入2继续该功能");
                        Scanner sc=new Scanner(System.in);
                        int choice1=Integer.parseInt(sc.next());
                        if(choice1==1){
                            break;
                        }
                    }
                }
                return;
            } else if (choice == 2) {
                customer.setMyShoppingCart(customer.getMyShoppingCart().removeGoodsFromCart());
                DataBase.updateCustomersCart(customer);
                System.out.println("已将购物车中的商品全部移除");
                return;
            } else if(choice==3){
                return;
            } else {
                System.out.println("输入错误请重新输入");
            }
        }
    }
    public void modifyGoodsFromCart(Customer customer){
        while (true){
            int ID,nums;
            System.out.println("*******修改购物车中的商品******");
            try {
                System.out.println("请输入商品编号");
                Scanner sc1=new Scanner(System.in);
                ID=sc1.nextInt();
                System.out.println("请输入修改后的商品数目：");
                Scanner sc2=new Scanner(System.in);
                nums=sc2.nextInt();
                if(customer.getMyShoppingCart().modifyGoodsNums(ID,nums)==null){
                    System.out.println("商品编号错误请重新输入");
                    continue;
                }
                customer.setMyShoppingCart(customer.getMyShoppingCart().modifyGoodsNums(ID,nums));
                DataBase.updateCustomersCart(customer);
                break;
            }catch (InputMismatchException exception){
                System.out.println("商品编号和商品数目都是整数，您输入的不是整型数据，请重新输入");
            }
            finally {
                System.out.println("输入1退回到上一界面，输入2继续该功能");
                Scanner sc=new Scanner(System.in);
                int choice1=Integer.parseInt(sc.next());
                if(choice1==1){
                    break;
                }
            }
        }
    }
    public void customerOfPaying(Customer customer){
        LinkedList<GoodsInCart> list=customer.getMyShoppingCart().checkOut();
        if(list==null){
            System.out.println("已取消支付");
            return;
        }
        customer.setMyHistoryShoppingCart(list);
        customer.setTotalSpent(customer.getMyShoppingCart().getTotalPrice());
        customer.setMyShoppingCart(new LinkedList<>());
        DataBase.updateCustomersCart(customer);
        System.out.println("支付成功");
    }
    public void searchForHistory(Customer customer){
        customer.getMyShoppingCart().printHistoryInfo();
    }
    //**************************************************************客户系统*************************************************************
}
