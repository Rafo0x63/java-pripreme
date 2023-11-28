package hr.java.production.model;

import java.io.Serializable;

/**
 * klasa za objekte kategorije
 */
public class Category extends NamedEntity implements Serializable {
    private String description;

    /**
     * @param name ime
     * @param description opis
     */
    public Category(Long id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
