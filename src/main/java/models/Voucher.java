package models;

import java.util.Date;

public class Voucher {
    private Integer id;
    private String code;
    private String description;
    private double minimumPrice;
    private double discountPercent;
    private Date expiryDate;
    private int availableTurns;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getAvailableTurns() {
        return availableTurns;
    }

    public void setAvailableTurns(int availableTurns) {
        this.availableTurns = availableTurns;
    }

    @Override
    public String toString() {
        return "Vouchers{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", minimumPrice=" + minimumPrice +
                ", discountPercent=" + discountPercent +
                ", expiryDate=" + expiryDate +
                ", availableTurns=" + availableTurns +
                '}';
    }
}
