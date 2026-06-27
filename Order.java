package com.example.project5;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Everything that has to do with all the orders
 * @author Angela Ding
 */
public class Order
{
    /** Assigns the order number starting at 1  */
    private static int NEXT_ORDER_NUMBER = 1;

    /** The order number  */
    private final int orderNumber;

    /** List of the menu items  */
    private final ArrayList<MenuItem> menuItems;
    /** The NJ Tax Rate  */
    public static final double NJ_TAX_RATE = 0.06625;

    /** Creates a new order and gives it an order number  */
    public Order()
    {
        this.orderNumber = NEXT_ORDER_NUMBER++;
        this.menuItems = new ArrayList<>();
    }
    /** Add items to the order  */
    public void addItem(MenuItem items)
    {
        menuItems.add(items);
    }
    /**Remove items from the order  */
    public void removeItem(MenuItem items)
    {
        menuItems.remove(items);
    }
    /**Clear all the items in the order  */
    public void clear()
    {
        menuItems.clear();
    }
    /** Compute the subtotal */
    public double getSubtotal()
    {
        double sum = 0.0;
        for(MenuItem item : menuItems)
        {
            sum += item.price();
        }
        return round(sum);
    }
    /** Compute tax from the subtotal */
    public double getTax()
    {
        return round(getSubtotal() * NJ_TAX_RATE);
    }
    /** compute total from subtotal and tax  */
    public double getTotal()
    {
        return round(getSubtotal() + getTax());
    }
    /** Getting the order number  */
    public int getOrderNumber()
    {
        return orderNumber;
    }
    /** List of the menu items */
    public ArrayList<MenuItem> getMenuItems()
    {
        return new ArrayList<>(menuItems);
    }
    /** writes content of order into the BufferedWriter  */
    public void writeTo(BufferedWriter bw) throws IOException
    {
        DecimalFormat df = new DecimalFormat("0.00");
        bw.write("com.example.project5.Order #" + orderNumber);
        bw.newLine();
        for(MenuItem menuItem : menuItems)
        {
            bw.write(" - " + menuItem.toString());
            bw.newLine();
        }
        bw.write("Subtotal: $" + df.format(getSubtotal()));
        bw.newLine();
        bw.write("Tax: $" + df.format(getTax()));
        bw.newLine();
        bw.write("Total: $" + df.format(getTotal()));
        bw.newLine();
        bw.write("--------------------------------------");
        bw.newLine();
    }
    /** Rounding the decimal to the currency */
    private double round(double value)
    {
        return Math.round(value * 100.0)/100.0;
    }
    /** Return as a String */
    @Override
    public String toString()
    {
        return "com.example.project5.Order #" + orderNumber + " ($" + getTotal() + ")";
    }
}
