package models;

import java.util.Date;
import java.util.Objects;

public class Products {
    private int id;
    private String name;
    private String categoryId;
    private String description;
    private double originalPrice;
    private double salePrice;
    private boolean visibility;
    private Date createAt;

    public Products() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", description='" + description + '\'' +
                ", originalPrice=" + originalPrice +
                ", salePrice=" + salePrice +
                ", visibility=" + visibility +
                ", createAt=" + createAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return id == products.id && Double.compare(originalPrice, products.originalPrice) == 0 && Double.compare(salePrice, products.salePrice) == 0 && visibility == products.visibility && Objects.equals(name, products.name) && Objects.equals(categoryId, products.categoryId) && Objects.equals(description, products.description) && Objects.equals(createAt, products.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryId, description, originalPrice, salePrice, visibility, createAt);
    }
}
