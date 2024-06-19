package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private String id;
    private int userId;
    private Date dateOrder;
    private int deliveryMethodId;
    private int paymentMethodId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private int orderStatusId;
    private int transactionStatusId;
    private int voucherId;
    private String message;
}
