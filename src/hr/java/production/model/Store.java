package hr.java.production.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * klasa koja sluzi za sve artikle koji su tipa store
 */
public class Store extends NamedEntity {
    private String webAddress;
    private Set<Item> items;

    /**
     * sluzi za instanciranje objekata klase Store
     *
     * @param name ime
     * @param webAddress stranica
     * @param items artikli
     */
    public Store(String name, String webAddress, Set<Item> items) {
        super(name);
        this.webAddress = webAddress;
        this.items = items;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
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
