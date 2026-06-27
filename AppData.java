package com.example.project5;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * Shared application data and Singleton design implementation to hold resources shared by other objects/threads
 * @author Angela Ding and Lucas Bandeira
 */

public class AppData
{

    /** Single instance of AppData  */
    private static AppData instance;


    /** Current order created  */
    private Order currentOrder;

    /** List of the placed order  */
    private ArrayList<Order> placedOrders;

    /** Private constructor for Singleton  */
    private AppData() {
        currentOrder = new Order();
        placedOrders = new ArrayList<>();
    }

    /** Return the instance of AppData on first call  */
    public static AppData getInstance()
    {
        if (instance == null)
            instance = new AppData();
        return instance;
    }

    /** Return the current order that user is currently on  */
    public Order getCurrentOrder()
    {
        return currentOrder;
    }

    /** Turns current order into placed order and after creates a new clear order  */
    public void placeCurrentOrder()
    {
        placedOrders.add(currentOrder);
        currentOrder = new Order();
    }

    /**Return list of placed order  */
    public List<Order> getPlacedOrders()
    {
        return Collections.unmodifiableList(placedOrders);
    }
}
