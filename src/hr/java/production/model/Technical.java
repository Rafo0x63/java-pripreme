package hr.java.production.model;

/**
 * koristi se za sve klase koje su tehnickog tipa
 */
public sealed interface Technical permits Laptop {
    /**
     *  @return vraca informaciju o duljini trajanja garancije
     */
    public Integer getWarranty();
}
