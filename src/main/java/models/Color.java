package models;

import java.util.Objects;

public class Color {
    private int id;
    private String codeColor;
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeColor() {
        return codeColor;
    }

    public void setCodeColor(String codeColor) {
        this.codeColor = codeColor;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Colors{" +
                "id=" + id +
                ", codeColor='" + codeColor + '\'' +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return id == color.id && Objects.equals(codeColor, color.codeColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeColor);
    }
}
