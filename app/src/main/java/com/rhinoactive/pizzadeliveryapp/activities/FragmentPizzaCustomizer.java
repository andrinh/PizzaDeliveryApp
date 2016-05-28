package com.rhinoactive.pizzadeliveryapp.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.rhinoactive.pizzadeliveryapp.R;

/**
 * Created by Huntur on 28/05/2016.
 */
public class FragmentPizzaCustomizer extends Fragment {

    private Spinner pizzaSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_pizza_customizer, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        pizzaSize = (Spinner) view.findViewById(R.id.spinnerPizzaSize);
    }
}
