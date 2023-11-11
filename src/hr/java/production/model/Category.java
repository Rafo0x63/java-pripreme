package hr.java.production.model;

/**
 * klasa za objekte kategorije
 */
public class Category extends NamedEntity {
    private String description;

    /**
     * @param name ime
     * @param description opis
     */
    public Category(String name, String description) {
        super(name);
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
