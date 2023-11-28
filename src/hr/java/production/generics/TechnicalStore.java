package hr.java.production.generics;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.model.Technical;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class TechnicalStore<T extends Technical> extends Store implements Serializable {

    private Set<T> technicalItems;
    public TechnicalStore(Long id, String name, String webAddress, Set<T> technicalItems) {
        super(id, name, webAddress);
        this.technicalItems = technicalItems;
    }

    @Override
    public Set<T> getItems() {
        return technicalItems;
    }
}
