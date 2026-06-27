package com.example.project5;

/**
 * com.example.project5.MenuItem class for  items on the menu
 * @author Angela Ding
 */
public abstract class MenuItem
{
    /** The number of items  */
    protected int quantity;

    /**
     * Menu Item with the quantity number
     * @param quantity the number of items that is 1 or greater
     * @throws IllegalArgumentException if number is less than 1
     */
    public MenuItem(int quantity)
    {
        if (quantity < 1)
        {
            throw new IllegalArgumentException("Quantity must be greater than or equal to 1.");
        }
        this.quantity = quantity;
    }
    /** Price of the items   */
    public abstract double price();

    /** Return quantity of the items  */
    public int getQuantity()
    {
        return quantity;
    }
    /**updating the number of the item  */
    public void setQuantity(int quantity)
    {
        if (quantity < 1)
        {
            throw new IllegalArgumentException("Quantity must be greater than or equal to 1.");
        }
        this.quantity = quantity;
    }
    /** description of item  */
    @Override
    public abstract String toString();

}