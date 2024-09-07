import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ShoppingCart {
    LinkedList<GoodsInCart> shoppingCartInfo=new LinkedList<>();
    LinkedList<GoodsInCart> historyInfo=new LinkedList<>();
    private double totalPrice;


    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(){
        double sum=0;
        for (GoodsInCart temp : this.shoppingCartInfo) {
            sum+=temp.getAllPrice();
        }
        this.totalPrice=sum;
    }
    public LinkedList<GoodsInCart> addGoodsToCart(int goodsID,int nums){
        GoodsInCart temp=new GoodsInCart();
        Goods goods = null;
        for(Goods temp2 : DataBase.goodsInfo){
            if(temp2.getGoodsID()==goodsID){
                goods=temp2;
                break;
            }
        }
        if(goods==null){
            System.out.println("商品编号错误");
            return null;
        }
        if(goods.getQuantity()<nums){
            System.out.println("您购买的商品数目过多，余货不足");
            return null;
        }
        temp.setGoods(goods);
        temp.setNums(nums);
        temp.setAllPrice();
        this.shoppingCartInfo.add(temp);
        this.setTotalPrice();
        return this.shoppingCartInfo;
    }
    public LinkedList<GoodsInCart> removeGoodsFromCart(int goodsID){
        for(GoodsInCart temp : this.shoppingCartInfo){
            if(temp.getGoods().getGoodsID()==goodsID){
                this.shoppingCartInfo.remove(temp);
                return this.shoppingCartInfo;
            }
        }
        return null;
    }
    public LinkedList<GoodsInCart> removeGoodsFromCart(){
        this.shoppingCartInfo.clear();
        return this.shoppingCartInfo;
    }
    public LinkedList<GoodsInCart> modifyGoodsNums(int goodsID,int newNums){
        if(newNums<=0){
            return this.removeGoodsFromCart(goodsID);
        }
        for(GoodsInCart temp : this.shoppingCartInfo){
            if(temp.getGoods().getGoodsID()==goodsID){
                int count=this.shoppingCartInfo.indexOf(temp);
                temp.setNums(newNums);
                this.shoppingCartInfo.set(count,temp);
                return this.shoppingCartInfo;
            }
        }
        return null;
    }
    public LinkedList<GoodsInCart> checkOut(){
        System.out.println("总金额为："+this.getTotalPrice()+"元是否确定进行支付？");
        System.out.println("确认支付请输入1   取消支付请输入2");
        Scanner sc=new Scanner(System.in);
        boolean isPay= sc.next() .equals("1");
        if(isPay){
            LocalDate now=LocalDate.now();
            String time=now.toString();
            for(GoodsInCart temp: this.shoppingCartInfo){
                DataBase.modifyGoodsNums(temp.getGoods().getGoodsID(), temp.getNums());
                temp.setBuyingDate(time);
                this.historyInfo.add(temp);
            }
            this.shoppingCartInfo.clear();
            return this.historyInfo;
        }
        return null;
    }
    public void printHistoryInfo(){
        if(historyInfo.isEmpty()){
            System.out.println("无历史购物记录");
        }
        else{
            System.out.printf("%10s%10s%10s\n","商品名称","商品数量","购买日期");
            for(GoodsInCart temp:this.historyInfo){
                System.out.printf("%10s%13d%15s\n",temp.getGoods().getGoodsName(),temp.getNums(),temp.getBuyingDate());
            }
        }
    }
    public void printInfoOfShoppingCart(){
        if(this.shoppingCartInfo==null||this.shoppingCartInfo.isEmpty()){
            System.out.println("无商品信息");
            return;
        }
        System.out.println("-------------------------------------所有商品信息如下----------------------------------------");
        System.out.printf("%10s%15s%13s%12s%10s\n","商品名称","生产厂家","商品型号","零售价格","商品数量");
        for (GoodsInCart c : this.shoppingCartInfo) {
            System.out.printf("%11s%15s%20s%18f%10d\n", c.getGoods().getGoodsName(), c.getGoods().getManufacturer(), c.getGoods().getModelNumber(), c.getGoods().getSellingPrice(), c.getNums());
        }
    }
}
