package cartShopping;

import models.Color;
import models.Product;
import models.Size;
import utils.FormatCurrency;

import java.util.Objects;

public abstract class AbstractCartProduct {
    protected Product product;
    protected int quantity;
    protected Color color;
    protected double priorityPrice;

    public AbstractCartProduct(Product product, int quantity, Color color) {
        this.product = product;
        this.quantity = quantity;
        this.color = color;
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
        if (this.product.getSalePrice() != 0) {
            priorityPrice = this.product.getSalePrice();
        } else {
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

    public abstract double getSewingPrice();

    public double getSubtotal() {
        return this.quantity * getSewingPrice();
    }

    public String sewingPriceFormat() {
        return FormatCurrency.vietNamCurrency(getSewingPrice());
    }

    public String subtotalFormat() {
        return FormatCurrency.vietNamCurrency(getSubtotal());
    }
}
