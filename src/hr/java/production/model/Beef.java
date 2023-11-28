package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * klasa koja sluzi za sve artikle koji su tipa beef
 */
public class Beef extends Item implements Edible, Serializable {
    private final int kcalPerKg = 2505;
    private BigDecimal weight;

    /**
     * sluzi za artikle vezane uz govedinu
     *
     * @param name ime
     * @param category kategorija
     * @param width sirina
     * @param height visina
     * @param length duljina
     * @param productionCost cijena proizvodnje
     * @param sellingPrice prodajna cijena
     * @param weight tezina
     * @param discount popust
     */
    public Beef(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, Discount discount) {
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

    /**
     * @return vraca kalorije s obzirom na tezinu proizvoda
     */
    @Override
    public Integer calculateKCal() {
        return this.weight.multiply(BigDecimal.valueOf(this.kcalPerKg)).intValue();
    }

    /**
     * @return vraca cijenu proizvodnje
     */
    @Override
    public BigDecimal calculateProductionPrice() {
        return this.weight.multiply(this.getProductionCost());
    }

    /**
     * @return vraca prodajnu cijenu
     */
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
