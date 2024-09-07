public class GoodsInCart {
    private Goods goods;
    private int nums;
    private double allPrice;
    String buyingDate;

    public String getBuyingDate() {
        return buyingDate;
    }

    public Goods getGoods() {
        return goods;
    }

    public int getNums() {
        return nums;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public void setAllPrice() {
        this.allPrice = this.nums * this.goods.getSellingPrice();
    }

    public void setBuyingDate(String buyingDate) {
        this.buyingDate = buyingDate;
    }
}
