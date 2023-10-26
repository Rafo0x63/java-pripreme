package hr.java.production.model;

import java.math.BigDecimal;

public interface Edible {
    public Integer calculateKCal();

    public BigDecimal calculateProductionPrice();

    public BigDecimal calculateSellingPrice();
}
