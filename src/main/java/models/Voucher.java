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
public class Voucher {
    private Integer id;
    private String code;
    private String description;
    private double minimumPrice;
    private double discountPercent;
    private Date expiryDate;
    private int availableTurns;
    private boolean state;
}
