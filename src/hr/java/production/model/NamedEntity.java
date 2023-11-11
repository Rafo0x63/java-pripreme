package hr.java.production.model;

/**
 * super klasa za klase s poljem "ime"
 */
public class NamedEntity {
    private String name;

    /**
     * sluzi za imenovanje svih objekata
     *
     * @param name ime
     */
    public NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

