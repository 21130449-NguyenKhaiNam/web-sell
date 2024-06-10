package dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class OrderResponseDTO {
    private String id;
    private int quantity;
    private Date dateOrder;
}
