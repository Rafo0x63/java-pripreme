package hr.java.production.model;

import java.io.Serializable;
import java.util.Set;

/**
 * klasa koja sluzi za sve artikle koji su tipa store
 */
public abstract class Store<T> extends NamedEntity implements Serializable {
    private Long id;
    private String webAddress;

    /**
     * sluzi za instanciranje objekata klase Store
     *
     * @param name ime
     * @param webAddress stranica
     */
    public Store(Long id, String name, String webAddress) {
        super(id, name);
        this.webAddress = webAddress;
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

    public abstract Set<T> getItems();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
