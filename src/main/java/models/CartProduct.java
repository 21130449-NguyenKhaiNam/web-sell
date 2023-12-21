package models;

import utils.FormatCurrency;
import utils.ProductFactory;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class CartProduct {
    private Product product;
    private int quantity;
    private Color color;
    private Size size;
    private double priorityPrice;

    public CartProduct(Product product, int quantity, Color color, Size size) {
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
        if(this.product.getSalePrice() != 0){
            priorityPrice = this.product.getSalePrice();
        }else{
            priorityPrice = this.product.getOriginalPrice();
        }
        return priorityPrice;
    }

    public void setPriorityPrice(double priorityPrice) {
        this.priorityPrice = priorityPrice;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getSewingPrice(){
        return getPriorityPrice() + this.size.getSizePrice();
    }

    public double getSubtotal(){
        return this.quantity * getSewingPrice();
    }

    public String sewingPriceFormat(){
        return FormatCurrency.vietNamCurrency(getSewingPrice());
    }

    public String subtotalFormat(){
        return FormatCurrency.vietNamCurrency(getSubtotal());
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
