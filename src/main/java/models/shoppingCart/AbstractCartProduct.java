package models.shoppingCart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Color;
import models.Product;
import models.Size;
import utils.FormatCurrency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public abstract String sizeRequired();

    public double getPriorityPrice() {
        if (this.product.getSalePrice() != 0) {
            priorityPrice = this.product.getSalePrice();
        } else {
            priorityPrice = this.product.getOriginalPrice();
        }
        return priorityPrice;
    }

    public String makeSizeFormat(){
        String sizeFormat = "Kích thước: ";
        sizeFormat += sizeRequired();
        if(this instanceof CartProduct){
            sizeFormat += " (giá kích thước: " + ((CartProduct)(this)).getSize().getSizePrice() + ")";
        }
        return sizeFormat;
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
