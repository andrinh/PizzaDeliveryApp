package com.rhinoactive.pizzadeliveryapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.rhinoactive.pizzadeliveryapp.R;
import com.rhinoactive.pizzadeliveryapp.model.Address;
import com.rhinoactive.pizzadeliveryapp.model.Customer;
import com.rhinoactive.pizzadeliveryapp.model.Order;
import com.rhinoactive.pizzadeliveryapp.model.Pizza;
import com.rhinoactive.pizzadeliveryapp.networking.PizzaHTTPClient;
import com.rhinoactive.pizzadeliveryapp.utils.Constants;
import com.rhinoactive.pizzadeliveryapp.utils.OnPizzaNumberItemSelectedListener;
import com.rhinoactive.pizzadeliveryapp.utils.PizzaOrderSendTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText firstName, lastName, email, street, city, postalCode;
    private Spinner provinceSpinner, numberOfPizzasSpinner;
    private Customer customer;
    private Address customerAddress;
    private List<Pizza> pizzas;
    private OnPizzaNumberItemSelectedListener onPizzaNumberItemSelectedListener;
    private List<EditText> editTextForms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListenerOnSubmitButton();
        addListenerOnSpinnerItemSelection();
    }

    private void init(){
        submitButton = (Button)findViewById(R.id.submitButton);
        firstName = (EditText)findViewById(R.id.editFirstNameText);
        lastName = (EditText)findViewById(R.id.editLastNameText);
        email = (EditText)findViewById(R.id.editEmailText);
        street = (EditText)findViewById(R.id.editStreetText);
        city = (EditText)findViewById(R.id.editCityText);
        postalCode = (EditText)findViewById(R.id.editPostalCodeText);
        provinceSpinner = (Spinner)findViewById(R.id.spinnerProvince);
        numberOfPizzasSpinner = (Spinner)findViewById(R.id.spinnerPizzaNumber);
        editTextForms = addAllEditTexts();
    }

    private void addListenerOnSubmitButton() {
        submitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                boolean formValid = isFormValid();
                if (!formValid) {
                    Toast.makeText(MainActivity.this, Constants.FORM_INCOMPLETE,
                            Toast.LENGTH_LONG).show();
                } else {
                    customer = new Customer(firstName.getText().toString(),
                            lastName.getText().toString(), email.getText().toString());
                    customerAddress = new Address(street.getText().toString(),
                            city.getText().toString(),
                            String.valueOf(provinceSpinner.getSelectedItem()),
                            postalCode.getText().toString());
                    pizzas = onPizzaNumberItemSelectedListener.getPizzas();
                    int orderID = calculateOrderId(customer.getFirstName(), customer.getLastName());
                    Order order = new Order(orderID, customer, customerAddress, pizzas);
                    sendOrder(order);
                }
            }
        });
    }

    private void addListenerOnSpinnerItemSelection() {
        numberOfPizzasSpinner.setOnItemSelectedListener(onPizzaNumberItemSelectedListener
                = new OnPizzaNumberItemSelectedListener(getFragmentManager()));
    }

    private int calculateOrderId(String customerFirstName, String customerLastName) {
        int orderId;
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTime = dateTimeFormat.format(new Date());
        String customerDateTime = customerFirstName + customerLastName + dateTime;
        orderId = customerDateTime.hashCode();
        return orderId;
    }

    private boolean isFormValid() {
        boolean formValid = onPizzaNumberItemSelectedListener.isPizzaFormValid();
        if (formValid) {
            formValid = isCustomerInfoValid();
        }
        return formValid;
    }

    private boolean isCustomerInfoValid() {
        boolean customerInfoValid = isProvinceSelected();
        if (customerInfoValid) {
            for (EditText editText : editTextForms) {
                if (editText.getText().toString().length() == 0) {
                    customerInfoValid = false;
                    break;
                }
            }
        }
        return customerInfoValid;
    }

    private boolean isProvinceSelected() {
        boolean provinceSelected = false;
        if (provinceSpinner.getSelectedItemPosition() != 0) {
            provinceSelected = true;
        }
        return provinceSelected;
    }

    private List<EditText> addAllEditTexts() {
        List<EditText> editTextForms = new ArrayList<>();
        editTextForms.add(firstName);
        editTextForms.add(lastName);
        editTextForms.add(email);
        editTextForms.add(street);
        editTextForms.add(city);
        editTextForms.add(postalCode);
        return  editTextForms;
    }

    private void clearFormFields() {
        for (EditText editText : editTextForms) {
            editText.setText("");
        }
        provinceSpinner.setSelection(0);
        numberOfPizzasSpinner.setSelection(0);
    }

    private void sendOrder(Order order) {
        PizzaOrderSendTask pizzaOrderSendTask = new PizzaOrderSendTask(MainActivity.this, order);
        pizzaOrderSendTask.execute();
        Toast.makeText(MainActivity.this, Constants.PLACING_ORDER,
                Toast.LENGTH_LONG).show();
    }
}
