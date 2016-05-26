package com.rhinoactive.pizzadeliveryapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huntur on 26/05/2016.
 */
public class Pizza {
    private String size;
    private List<Condiment> condiments;

    public Pizza(String size, ArrayList<Condiment> condiments) {
        this.size = size;
        this.condiments = condiments;
    }

    public String getSize() {
        return size;
    }

    public List<Condiment> getCondiments() {
        return condiments;
    }
}
