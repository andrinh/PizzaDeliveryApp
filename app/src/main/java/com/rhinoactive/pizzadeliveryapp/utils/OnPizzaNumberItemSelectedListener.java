package com.rhinoactive.pizzadeliveryapp.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huntur on 27/05/2016.
 */
public class OnPizzaNumberItemSelectedListener implements OnItemSelectedListener {
    private LinearLayout mainLayout;
    private LinearLayout.LayoutParams params;
    private List<LinearLayout> pizzaContainers;

    public OnPizzaNumberItemSelectedListener(LinearLayout mainLayout) {
        this.mainLayout = mainLayout;
        params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pizzaContainers = new ArrayList<>();

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        removeExistingPizzaContainers();
        int numberOfPizzas = pos;
        for (int pizzaNumber = 1; pizzaNumber <= numberOfPizzas; pizzaNumber++) {
            LinearLayout pizzaContainer = createPizzaContainerLayout(parent);
            TextView pizzaHeader = new TextView(parent.getContext());
            pizzaHeader.setText(Constants.PIZZA + " " + pizzaNumber);
            pizzaContainer.addView(pizzaHeader);
            pizzaContainers.add(pizzaContainer);
            mainLayout.addView(pizzaContainer);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void removeExistingPizzaContainers(){
        for (LinearLayout pizzaContainer : pizzaContainers) {
            pizzaContainer.removeAllViews();
        }
        pizzaContainers.removeAll(pizzaContainers);
    }

    private LinearLayout createPizzaContainerLayout(AdapterView<?> parent){
        LinearLayout pizzaContainer = new LinearLayout(parent.getContext());
        pizzaContainer.setOrientation(LinearLayout.VERTICAL);
        pizzaContainer.setLayoutParams(params);
        return pizzaContainer;
    }
}
