public class Goods {
    private int goodsID;
    private String goodsName;
    private String manufacturer;
    private String producedTime;
    private String modelNumber;//商品型号
    private double purchasePrice;
    private double sellingPrice;
    private int quantity;

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getProducedTime() {
        return producedTime;
    }

    public void setGoodsID(int goodsID) {
        this.goodsID = goodsID;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setProducedTime(String producedTime) {
        this.producedTime = producedTime;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
