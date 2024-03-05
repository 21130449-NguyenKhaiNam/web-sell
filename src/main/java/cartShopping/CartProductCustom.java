package cartShopping;

import models.Color;
import models.Product;

import java.util.Objects;

public class CartProductCustom extends AbstractCartProduct {
    private String jsonSize;

    public CartProductCustom(Product product, int quantity, Color color, String jsonSize) {
        super(product, quantity, color);
        this.jsonSize = jsonSize;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public String getJsonSize() {
        return jsonSize;
    }

    public void setJsonSize(String jsonSize) {
        this.jsonSize = jsonSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductCustom that = (CartProductCustom) o;
        return Objects.equals(product, that.product) && Objects.equals(color, that.color) && Objects.equals(jsonSize, that.jsonSize);
    }

    @Override
    public double getSewingPrice() {
        return getPriorityPrice();
    }
}
