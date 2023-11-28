package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * record za dodavanje popusta na proizvode
 * @param discountAmount iznos popusta
 */
public record Discount(BigDecimal discountAmount) implements Serializable {

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
