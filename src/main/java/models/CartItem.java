package models;

import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Data
public class CartItem {
    @ColumnName("id")
    private Integer cartId;
    @ColumnName("product_id")
    private Integer productId;
    private Integer size;
    @ColumnName("color_id")
    private Integer colorId;
    private String sizeCustomize;
    private Integer quantity;

}
