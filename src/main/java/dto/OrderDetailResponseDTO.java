package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetailResponseDTO implements Serializable {
    private String orderId;
    private String status;
    private String fullName;
    private String phone;
    private String email;
    private String province;
    private String district;
    private String ward;
    private String detail;
    private String payment;
    private String voucherId;
    private Date orderDate;
    List<OrderItemResponseDTO> orderItems;
}
