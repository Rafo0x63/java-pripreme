package hr.java.production.enums;

public enum Cities {

    ZAGREB("10000", "Zagreb"),
    ZABOK("49210", "Zabok"),
    KRAPINA("49000", "Krapina"),

    KARLOVAC("47000","Karlovac");

    private final String postalCode;
    private final String name;
    Cities(String postalCode, String name) {
        this.postalCode = postalCode;
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getName() {
        return name;
    }
}
