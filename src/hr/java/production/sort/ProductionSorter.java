package hr.java.production.sort;

import hr.java.production.model.Item;

import java.util.Comparator;

public class ProductionSorter implements Comparator<Item> {

    private boolean ascending;

    public ProductionSorter(boolean ascending) {
        this.ascending = ascending;
    }
    @Override
    public int compare(Item i1, Item i2) {
        int result = i1.getSellingPrice().compareTo(i2.getSellingPrice());

        return ascending ? result : -result;

    }
}
