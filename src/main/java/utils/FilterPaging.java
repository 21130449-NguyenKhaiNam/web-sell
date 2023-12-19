package utils;

import models.Product;

import java.util.List;

public class FilterPaging {
    private List<Product> productCardList;
    private List<Integer> categoryId;
    private List<MoneyRange> moneyRange;
    private List<String> size;

    public List<Integer> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Integer> categoryId) {
        this.categoryId = categoryId;
    }

    public List<MoneyRange> getMoneyRange() {
        return moneyRange;
    }

    public void setMoneyRange(List<MoneyRange> moneyRange) {
        this.moneyRange = moneyRange;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String generator() {
        return "?/";
    }
}
