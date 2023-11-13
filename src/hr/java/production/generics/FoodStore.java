package hr.java.production.generics;

import hr.java.production.model.Edible;
import hr.java.production.model.Store;

import java.util.Set;

public class FoodStore<T extends Edible> extends Store {

    private Set<T> edibleItems;
    public FoodStore(String name, String webAddress, Set<T> edibleItems) {
        super(name, webAddress);
        this.edibleItems = edibleItems;
    }

    @Override
    public Set<T> getItems() {
        return edibleItems;
    }
}
