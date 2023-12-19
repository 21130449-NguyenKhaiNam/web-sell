package models;

import java.util.Objects;

public class CartProduct {
    private Product product;
    private int quantity;
    private String color;
    private String size;
    private double priorityPrice;

    public CartProduct(Product product, int quantity, String color, String size) {
        this.product = product;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriorityPrice() {
        return priorityPrice;
    }

    public void setPriorityPrice(double priorityPrice) {
        this.priorityPrice = priorityPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct that = (CartProduct) o;
        return Objects.equals(product, that.product) && Objects.equals(color, that.color) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, color, size);
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", priorityPrice=" + priorityPrice +
                '}';
    }
}
