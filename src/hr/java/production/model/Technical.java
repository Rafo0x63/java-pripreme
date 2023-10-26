package hr.java.production.model;

public sealed interface Technical permits Laptop {
    public Integer getWarranty();
}
