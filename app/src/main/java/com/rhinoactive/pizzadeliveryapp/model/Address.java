package com.rhinoactive.pizzadeliveryapp.model;

/**
 * Created by Huntur on 26/05/2016.
 */
public class Address {
    private String streetName;
    private String city;
    private String province;
    private String postalCode;

    public Address(String streetName, String city, String province, String postalCode) {
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
