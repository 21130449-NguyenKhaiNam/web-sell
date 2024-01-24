package models;

import java.util.Date;

public class Order {
    private int id;
    private String userId;
    private Date dateOrder;
    private String paymentMethod;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String statusOrder;
    private String statusTransaction;
    private int voucherId;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public String getStatusTransaction() {
        return statusTransaction;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public void setStatusTransaction(String statusTransaction) {
        this.statusTransaction = statusTransaction;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", dateOrder=" + dateOrder +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", statusOrder='" + statusOrder + '\'' +
                ", statusTransaction='" + statusTransaction + '\'' +
                ", voucherId=" + voucherId +
                '}';
    }
}
