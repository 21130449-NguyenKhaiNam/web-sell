package models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
@NoArgsConstructor
@Data
public class CartItem {
    private Integer id;
    @ColumnName("cart_id")
    private Integer cartId;
    @ColumnName("product_id")
    private Integer productId;
    private Object size;
    @ColumnName("color_id")
    private Integer colorId;
    private Integer quantity;

}
