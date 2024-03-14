package models;

import java.util.Objects;

public class PaymentMethod {
    private int id;
    private String typePayment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return id == that.id && Objects.equals(typePayment, that.typePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typePayment);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", typePayment='" + typePayment + '\'' +
                '}';
    }
}
