package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.Voucher;

import java.util.List;

@Data
@NoArgsConstructor
public class VoucherDTO {
    Voucher voucher;
    String state;
    List<Integer> listIdProduct;
}
