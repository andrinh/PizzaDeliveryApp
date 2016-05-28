package com.rhinoactive.pizzadeliveryapp.utils;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Huntur on 27/05/2016.
 */
public class OnPizzaNumberItemSelectedListener implements OnItemSelectedListener {
    private LinearLayout mainLayout;

    public OnPizzaNumberItemSelectedListener(LinearLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        int numberOfPizzas = pos;
        for (int i = 1; i <= numberOfPizzas; i++) {
            TextView pizzaHeader = new TextView(parent.getContext());
            pizzaHeader.setText(Constants.PIZZA + " " + i);
            mainLayout.addView(pizzaHeader);
            mainLayout.addView();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
