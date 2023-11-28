package hr.java.production.generics;

import hr.java.production.model.Edible;
import hr.java.production.model.Store;

import java.io.Serializable;
import java.util.Set;

public class FoodStore<T extends Edible> extends Store implements Serializable {

    private Set<T> edibleItems;
    public FoodStore(Long id, String name, String webAddress, Set<T> edibleItems) {
        super(id, name, webAddress);
        this.edibleItems = edibleItems;
    }

    @Override
    public Set<T> getItems() {
        return edibleItems;
    }
}
