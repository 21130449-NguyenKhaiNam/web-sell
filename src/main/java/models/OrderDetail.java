package models;

public class OrderDetail {
    private int id;
    private String orderId;
    private int productId;
    private String productName;
    private String sizeRequired;
    private String colorRequired;
    private int quantityRequired;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSizeRequired() {
        return sizeRequired;
    }

    public void setSizeRequired(String sizeRequired) {
        this.sizeRequired = sizeRequired;
    }

    public String getColorRequired() {
        return colorRequired;
    }

    public void setColorRequired(String colorRequired) {
        this.colorRequired = colorRequired;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", sizeRequired='" + sizeRequired + '\'' +
                ", colorRequired='" + colorRequired + '\'' +
                ", quantityRequired=" + quantityRequired +
                ", price=" + price +
                '}';
    }
}
