package com.rhinoactive.pizzadeliveryapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

import com.rhinoactive.pizzadeliveryapp.R;
import com.rhinoactive.pizzadeliveryapp.model.Address;
import com.rhinoactive.pizzadeliveryapp.model.Customer;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText firstName, lastName, email, street, city, postalCode;
    private Spinner province;
    private Customer customer;
    private Address customerAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListenerOnSubmitButton();
    }

    private void init(){
        submitButton = (Button)findViewById(R.id.submitButton);
        firstName = (EditText)findViewById(R.id.editFirstNameText);
        lastName = (EditText)findViewById(R.id.editLastNameText);
        email = (EditText)findViewById(R.id.editEmailText);
        street = (EditText)findViewById(R.id.editStreetText);
        city = (EditText)findViewById(R.id.editCityText);
        postalCode = (EditText)findViewById(R.id.editPostalCodeText);
        province = (Spinner)findViewById(R.id.spinnerProvince);
    }

    private void addListenerOnSubmitButton() {
        submitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                customer = new Customer(firstName.getText().toString(),
                        lastName.getText().toString(), email.getText().toString());
                customerAddress = new Address(street.getText().toString(),
                        city.getText().toString(), String.valueOf(province.getSelectedItem()),
                        postalCode.getText().toString());

            }
        });
    }
}
