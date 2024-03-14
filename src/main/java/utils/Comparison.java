package utils;

import models.Product;

public class Comparison {
    public static Product different2Product(Product product, Product productOther) {
        Product result = new Product();
        result.setId(0);

        if (product.getName() != null && productOther.getName() != null &&
                !product.getName().equals(productOther.getName()))
            result.setName(productOther.getName());

        if (product.getCategoryId() != productOther.getCategoryId())
            result.setCategoryId(productOther.getCategoryId());

        if (product.getDescription() != null && productOther.getDescription() != null && !product.getDescription().equals(productOther.getDescription()))
            result.setDescription(productOther.getDescription());

        if (product.getOriginalPrice() != productOther.getOriginalPrice())
            result.setOriginalPrice(productOther.getOriginalPrice());
        else
            result.setOriginalPrice(-1);

        if (product.getSalePrice() != productOther.getSalePrice())
            result.setSalePrice(productOther.getOriginalPrice());
        else
            result.setSalePrice(-1);

        result.setCreateAt(productOther.getCreateAt());

        return result;
    }
}
