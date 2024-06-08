package dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Voucher;
import services.voucher.VoucherState;

import java.util.List;

@Data
@NoArgsConstructor
public class VoucherDTO {
    Voucher voucher;
    String state;
    List<Integer> listIdProduct;
}
