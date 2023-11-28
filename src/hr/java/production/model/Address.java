package hr.java.production.model;


import hr.java.production.enums.Cities;

import java.io.Serializable;

/**
 * klasa koja sluzi za sve artikle koji su tipa adresa
 */
public class Address implements Serializable {
    private String street;
    private String houseNumber;
    private Cities city;
    /**
     * sluzi builderu za instanciranje objekta
     *
     * @param street ulica
     * @param houseNumber broj ulice
     * @param city grad
     * @param
     * */
    private Address(String street, String houseNumber, Cities city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }


    public static class Builder {
        private String street;
        private String houseNumber;
        private Cities city;

        public Builder(String street) {
            this.street = street;
        }

        public Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder setCity(Cities city) {
            this.city = city;
            return this;
        }



        /**
         * instancira objekt tipa Address
         * @return vraca objekt tipa Address
         */
        public Address build() {
            return new Address(this.street, this.houseNumber, this.city);
        }

    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public String getPostalCode() {
        return city.getPostalCode();
    }
    public String getCityName() {
        return city.getName();
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
