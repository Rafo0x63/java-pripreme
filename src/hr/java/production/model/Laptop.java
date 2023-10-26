package hr.java.production.model;

import java.math.BigDecimal;

public final class Laptop extends Item implements Technical {

    private Integer warranty;

    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Discount discount, Integer warranty) {
        super(name, category, width, height, length, productionCost, sellingPrice, discount);
        this.warranty = warranty;
    }

    @Override
    public Integer getWarranty() {
        return this.warranty;
    }
}
