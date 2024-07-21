package dto;

import lombok.Data;
import models.Product;

import java.util.Map;

@Data
public class DashBoardDetail {
    private Long orderSuccess;
    private Long orderFailed;
    private Double revenue;
    private Map<Product, Integer> productPopular;
    private Map<Product, Integer> productNotPopular;
}
