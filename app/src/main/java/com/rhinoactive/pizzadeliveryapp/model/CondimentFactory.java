package com.rhinoactive.pizzadeliveryapp.model;

import com.rhinoactive.pizzadeliveryapp.model.condiments.Mushroom;
import com.rhinoactive.pizzadeliveryapp.model.condiments.Pepperoni;

/**
 * Created by Huntur on 27/05/2016.
 */
public class CondimentFactory {
    public Condiment createCondiment(String condimentType){
        condimentType = condimentType.toLowerCase();
        Condiment condiment = null;
        if (condimentType.equals("pepperoni")){
            condiment = new Pepperoni(condimentType);
        } else if (condimentType.equals("mushroom")){
            condiment = new Mushroom(condimentType);
        }
        return condiment;
    }
}
