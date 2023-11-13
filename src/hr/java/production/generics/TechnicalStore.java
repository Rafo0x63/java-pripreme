package hr.java.production.generics;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.model.Technical;

import java.util.List;
import java.util.Set;

public class TechnicalStore<T extends Technical> extends Store {

    private Set<T> technicalItems;
    public TechnicalStore(String name, String webAddress, Set<T> technicalItems) {
        super(name, webAddress);
        this.technicalItems = technicalItems;
    }

    @Override
    public Set<T> getItems() {
        return technicalItems;
    }
}
