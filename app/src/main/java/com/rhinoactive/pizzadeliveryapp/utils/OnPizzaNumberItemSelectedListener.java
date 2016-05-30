package com.rhinoactive.pizzadeliveryapp.utils;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rhinoactive.pizzadeliveryapp.R;
import com.rhinoactive.pizzadeliveryapp.activities.FragmentPizzaCustomizer;
import com.rhinoactive.pizzadeliveryapp.model.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huntur on 27/05/2016.
 */
public class OnPizzaNumberItemSelectedListener implements OnItemSelectedListener {
    private List<FragmentPizzaCustomizer> pizzaContainers;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public OnPizzaNumberItemSelectedListener(FragmentManager fragmentManager) {
        pizzaContainers = new ArrayList<>();
        this.fragmentManager = fragmentManager;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        fragmentTransaction = fragmentManager.beginTransaction();
        removeExistingPizzaContainers();
        fragmentTransaction.commit();
        int numberOfPizzas = pos;
        for (int pizzaNumber = 1; pizzaNumber <= numberOfPizzas; pizzaNumber++) {
            addPizzaCustomizer(pizzaNumber);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public List<Pizza> getPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        for (FragmentPizzaCustomizer pizzaCustomizer : pizzaContainers) {
            String pizzaSize = pizzaCustomizer.getPizzaSize();
            List<String> condiments = pizzaCustomizer.getCondiments();
            Pizza pizza = new Pizza(pizzaSize, condiments);
            pizzas.add(pizza);
        }
        return pizzas;
    }

    public boolean isPizzaFormValid() {
        boolean pizzaFormsValid = true;
        if (pizzaContainers.size() == 0){
            pizzaFormsValid = false;
        }
        if (pizzaFormsValid) {
            for (FragmentPizzaCustomizer pizzaCustomizer : pizzaContainers) {
                if (!pizzaCustomizer.isPizzaSizeSelected()) {
                    pizzaFormsValid = false;
                    break;
                }
            }
        }
        return pizzaFormsValid;
    }

    private void removeExistingPizzaContainers(){
        for (FragmentPizzaCustomizer pizzaContainer : pizzaContainers) {
            fragmentTransaction.remove(pizzaContainer);
        }
        List<FragmentPizzaCustomizer> allPizzaContainers = pizzaContainers;
        pizzaContainers.removeAll(allPizzaContainers);
    }

    private void addPizzaCustomizer(int pizzaNumber){
        fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.FRAGEMENT_PIZZA_NUMBER_CUSTOMIZER_KEY, pizzaNumber);
        FragmentPizzaCustomizer fragmentPizzaCustomizer = new FragmentPizzaCustomizer();
        fragmentPizzaCustomizer.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_pizza_customizer_container,
                fragmentPizzaCustomizer);
        pizzaContainers.add(fragmentPizzaCustomizer);
        fragmentTransaction.commit();
    }
}
