package models;

import java.util.Objects;

public class Size {
    private int id;
    private String nameSize;
    private int productId;
    private double sizePrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getSizePrice() {
        return sizePrice;
    }

    public void setSizePrice(double sizePrice) {
        this.sizePrice = sizePrice;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"nameSize\":\"" + nameSize + "\"" +
                ", \"productId\":\"" + productId +
                "\", \"sizePrice\":\"" + sizePrice +
                "\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return id == size.id && Double.compare(sizePrice, size.sizePrice) == 0 && Objects.equals(nameSize, size.nameSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSize, sizePrice);
    }
}
