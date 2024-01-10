package models;

import java.util.Objects;

public class DeliveryMethod {
    private Integer id;
    private String typeShipping;
    private String description;
    private double shippingFee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeShipping() {
        return typeShipping;
    }

    public void setTypeShipping(String typeShipping) {
        this.typeShipping = typeShipping;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryMethod that = (DeliveryMethod) o;
        return id == that.id && Double.compare(shippingFee, that.shippingFee) == 0 && Objects.equals(typeShipping, that.typeShipping) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeShipping, description, shippingFee);
    }

    @Override
    public String toString() {
        return "DeliveryMethod{" +
                "id=" + id +
                ", typeShipping='" + typeShipping + '\'' +
                ", description='" + description + '\'' +
                ", shippingFee=" + shippingFee +
                '}';
    }
}
