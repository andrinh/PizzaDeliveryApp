package com.rhinoactive.pizzadeliveryapp.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rhinoactive.pizzadeliveryapp.model.Order;
import com.rhinoactive.pizzadeliveryapp.networking.PizzaHTTPClient;

import okhttp3.Call;

/**
 * Created by Huntur on 30/05/2016.
 */
public class PizzaOrderSendTask extends AsyncTask<Void, Void, Void> {
    private  Context context;
    private boolean orderSent;
    private Order order;

    public PizzaOrderSendTask(Context context, Order order) {
        this.context = context;
        this.order = order;
        orderSent = false;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Call orderRequest = PizzaHTTPClient.orderPizza(order);
            orderRequest.execute();
            orderSent = true;
        }catch (Exception pizzaOrderException) {
            orderSent = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (orderSent) {
            Toast.makeText(context, Constants.ORDER_PLACED,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, Constants.ORDER_ERROR,
                    Toast.LENGTH_LONG).show();
        }
    }
}
