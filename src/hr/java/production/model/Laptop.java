package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * klasa koja sluzi za sve artikle koji su tipa laptop
 */
public final class Laptop extends Item implements Technical, Serializable {
    private Integer warranty;

    /**
     * sluzi za instanciranje objekata klase Laptop
     *
     * @param name ime
     * @param category kategorija
     * @param width sirina
     * @param height visina
     * @param length duljina
     * @param productionCost cijena proizvodnje
     * @param sellingPrice prodajna cijena
     * @param discount popust
     * @param warranty garancija
     */
    public Laptop(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Discount discount, Integer warranty) {
        super(id, name, category, width, height, length, productionCost, sellingPrice, discount);
        this.warranty = warranty;
    }

    @Override
    public Integer getWarranty() {
        return this.warranty;
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
