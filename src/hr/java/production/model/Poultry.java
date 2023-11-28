package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * klasa koja sluzi za sve artikle koji su tipa poultry
 */
public class Poultry extends Item implements Edible, Serializable {
    private final int kcalPerKg = 2390;
    private BigDecimal weight;

    /**
     * sluzi za instanciranje objekata klase Poultry
     *
     * @param name ime
     * @param category kategorija
     * @param width sirina
     * @param height visina
     * @param length duljina
     * @param productionCost cijena proizvodnje
     * @param sellingPrice prodajna cijena
     * @param discount popust
     */
    public Poultry(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, Discount discount) {
        super(id, name, category, width, height, length, productionCost, sellingPrice, discount);
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
