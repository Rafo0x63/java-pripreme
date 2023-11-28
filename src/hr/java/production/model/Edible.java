package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * sucelje koje sluzi za objekte koji us jestivi
 */
public interface Edible {

    public Integer calculateKCal();

    public BigDecimal calculateProductionPrice();

    public BigDecimal calculateSellingPrice();

}
