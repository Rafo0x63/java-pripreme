package hr.java.production.model;

public class Factory extends NamedEntity {
    private Address address;
    private Item[] items;

    public Factory(String name, Address address, Item[] items) {
        super(name);
        this.address = address;
        this.items = items;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}