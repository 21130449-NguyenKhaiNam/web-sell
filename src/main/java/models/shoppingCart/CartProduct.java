package models.shoppingCart;

import models.Color;
import models.Product;
import models.Size;
import utils.FormatCurrency;

import java.util.Objects;

public class CartProduct extends AbstractCartProduct{
    private Size size;

    public CartProduct(Product product, int quantity, Color color, Size size) {
        super(product, quantity, color);
        this.size = size;
    }

    public String getSize() {
        return size.toString();
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String sizeRequired() {
        return size.getNameSize();
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
                "size=" + size +
                ", product=" + product +
                ", quantity=" + quantity +
                ", color=" + color +
                ", priorityPrice=" + priorityPrice +
                '}';
    }

    public Size getInstanceSize() {
        return size;
    }
}
