package cartShopping;

import models.Color;
import models.Product;
import models.Size;
import utils.FormatCurrency;

import java.util.Objects;

public class CartProduct extends AbstractCartProduct {
    private Size size;

    public CartProduct(Product product, int quantity, Color color, Size size) {
        super(product, quantity, color);
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct that = (CartProduct) o;
        return Objects.equals(product, that.product) && Objects.equals(color, that.color) && Objects.equals(size, that.size);
    }

    public Size getSize() {
        return size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, color, size);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getSewingPrice() {
        return getPriorityPrice() + this.size.getSizePrice();
    }

    public double getSubtotal() {
        return this.quantity * getSewingPrice();
    }

}
