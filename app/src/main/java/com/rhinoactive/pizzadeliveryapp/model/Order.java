package com.rhinoactive.pizzadeliveryapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/25/16.
 */
public class Order {

    private int orderId;
    private Customer customer;
    private Address customerAddress;
    private List<Pizza> pizzas;

    public Order(int orderId, Customer customer, Address customerAddress, List<Pizza> pizzas){
        this.orderId = orderId;
        this.customer = customer;
        this.customerAddress = customerAddress;
        this.pizzas = pizzas;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
}
