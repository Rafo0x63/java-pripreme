package hr.java.production.model;

import java.math.BigDecimal;

public class Beef extends Item implements Edible {
    private final int kcalPerKg = 2505;

    private BigDecimal weight;

    public Beef(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, Discount discount) {
        super(name, category, width, height, length, productionCost, sellingPrice, discount);
        this.weight = weight;
    }

    public int getKcalPerKg() {
        return kcalPerKg;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public Integer calculateKCal() {
        return this.weight.multiply(BigDecimal.valueOf(this.kcalPerKg)).intValue();
    }

    @Override
    public BigDecimal calculateProductionPrice() {
        return this.weight.multiply(this.getProductionCost());
    }

    @Override
    public BigDecimal calculateSellingPrice() {
        return this.weight.multiply(this.getSellingPrice());
    }
}
