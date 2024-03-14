package models.shoppingCart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Color;
import models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CartProductCustom extends AbstractCartProduct {
    private String jsonSize;

    public CartProductCustom(Product product, int quantity, Color color, String jsonSize) {
        super(product, quantity, color);
        this.jsonSize = jsonSize;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public String getJsonSize() {
        return jsonSize;
    }

    public void setJsonSize(String jsonSize) {
        this.jsonSize = jsonSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductCustom that = (CartProductCustom) o;
        return Objects.equals(product, that.product) && Objects.equals(color, that.color) && Objects.equals(jsonSize, that.jsonSize);
    }

    @Override
    public String sizeRequired() {
        String parametersSizeFormat = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> mapParametersSize = objectMapper.readValue(jsonSize, new TypeReference<Map<String, String>>() {});
            List<String> listEntrySize = new ArrayList<>();
            for (Map.Entry<String, String> entrySize : mapParametersSize.entrySet()) {
                listEntrySize.add(entrySize.getKey() + ": " + entrySize.getValue() + " cm");
            }
            parametersSizeFormat = String.join(", ", listEntrySize);

        }catch (IOException exception){
            exception.printStackTrace();
        }
        return parametersSizeFormat;
    }

    @Override
    public double getSewingPrice() {
        return getPriorityPrice();
    }
}
