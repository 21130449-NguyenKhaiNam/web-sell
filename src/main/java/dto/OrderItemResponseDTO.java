package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemResponseDTO implements Serializable {
    private int id;
    private String name;
    private String size;
    private String color;
    private int quantity;
    private double price;
    private String thumbnail;
}
