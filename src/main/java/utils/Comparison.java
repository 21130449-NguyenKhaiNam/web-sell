package utils;

import models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    //    Chỉ dùng cho update (xác định xem ngừoi dùng chọn insert, update hay delete)
    public static <T> Map<String, List<T>> filterTypeQuery(List<T> inRequest, List<T> inDb) {
//        Nếu inRequest có, inDB không có thì INSERT
//        Nếu inRequest có, inDB có thì UPDATE
//        Nếu inRequest không có, inDB có thì DELETE
        Map<String, List<T>> result = new HashMap<>();
        List<T> listIdInsert = inRequest.stream().filter(item -> !inDb.contains(item)).collect(Collectors.toList());
        List<T> listIdUpdate = inRequest.stream().filter(item -> inDb.contains(item)).collect(Collectors.toList());
        List<T> listIdDelete = inDb.stream().filter(item -> !inRequest.contains(item)).collect(Collectors.toList());
        result.put("insert", listIdInsert);
        result.put("update", listIdUpdate);
        result.put("delete", listIdDelete);
        return result;
    }
}
