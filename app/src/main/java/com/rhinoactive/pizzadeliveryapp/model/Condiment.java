package com.rhinoactive.pizzadeliveryapp.model;

/**
 * Created by Huntur on 26/05/2016.
 */
public abstract class Condiment {
    private String name;

    public Condiment(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
