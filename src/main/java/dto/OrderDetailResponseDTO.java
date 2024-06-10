package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailResponseDTO implements Serializable {
    private String orderId;
    private Date orderDate;
    private String status;
    private String fullName;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String detail;
    private String payment;
    private String voucher;
    List<OrderItemResponseDTO> orderItems;
}
