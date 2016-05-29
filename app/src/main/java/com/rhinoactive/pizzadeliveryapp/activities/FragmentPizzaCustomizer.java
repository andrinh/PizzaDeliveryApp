package com.rhinoactive.pizzadeliveryapp.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.rhinoactive.pizzadeliveryapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huntur on 28/05/2016.
 */
public class FragmentPizzaCustomizer extends Fragment {

    private Spinner pizzaSize;
    private CheckBox checkMushroom, checkPepperoni;
    private List<CheckBox> condimentCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_pizza_customizer, container, false);
        init(view);
        return view;
    }

    public String getPizzaSize() {
        String size = String.valueOf(pizzaSize.getSelectedItem());
        return size;
    }

    public List<String> getCondiments() {
        List<String> condiments = new ArrayList<>();
        for (CheckBox condimentCheckBox : condimentCheckBoxes) {
            if (condimentCheckBox.isChecked()) {
                String condiment = condimentCheckBox.getText().toString();
                condiments.add(condiment);
            }
        }
        return condiments;
    }

    public boolean isPizzaSizeSelected(){
        boolean sizeSelected = false;
        if (pizzaSize.getSelectedItemPosition() != 0) {
            sizeSelected = true;
        }
        return sizeSelected;
    }

    private void init(View view){
        pizzaSize = (Spinner) view.findViewById(R.id.spinnerPizzaSize);
        checkMushroom = (CheckBox) view.findViewById(R.id.checkMushroom);
        checkPepperoni = (CheckBox) view.findViewById(R.id.checkPepperoni);
        condimentCheckBoxes = new ArrayList<>();
        condimentCheckBoxes.add(checkMushroom);
        condimentCheckBoxes.add(checkPepperoni);
    }
}
