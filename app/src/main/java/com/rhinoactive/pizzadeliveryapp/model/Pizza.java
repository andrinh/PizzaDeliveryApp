package com.rhinoactive.pizzadeliveryapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huntur on 26/05/2016.
 */
public class Pizza {
    private String size;
    private List<Condiment> condiments;
    private CondimentFactory condimentFactory;

    public Pizza(String size, ArrayList<String> condimentTypes) {
        this.size = size;
        condimentFactory = new CondimentFactory();
        for (String condimentType: condimentTypes){
            addCondiment(condimentType);
        }
    }

    public void addCondiment(String condimentType){
        condiments.add(condimentFactory.createCondiment(condimentType));
    }

    public String getSize() {
        return size;
    }

    public List<Condiment> getCondiments() {
        return condiments;
    }
}
