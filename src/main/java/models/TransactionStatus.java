package models;

import java.util.Objects;

public class TransactionStatus {
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
        return "TransactionStatus{" +
                "id=" + id +
                ", typeStatus='" + typeStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionStatus that = (TransactionStatus) o;
        return id == that.id && Objects.equals(typeStatus, that.typeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeStatus);
    }
}
