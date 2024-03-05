package models;

import java.util.Objects;

public class OrderStatus {
    private int id;
    private String typeStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", typeStatus='" + typeStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return id == that.id && Objects.equals(typeStatus, that.typeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeStatus);
    }
}
