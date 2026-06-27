package com.example.project5;

import java.util.ArrayList;
import java.util.List;

/**
 *This class represents a coffee menu items.
 *  * Each com.example.project5.Coffee menu item has a cup size, add-ins, and quanity.
 *  * Using these attributes, the price is determined and calc'd by:
 *  *  - Base price for Short Black com.example.project5.Coffee
 *  *  - An increment in price for each larger cup size
 *  *  - A fixed added price per add-in
 *  *  - And the number of coffee items ordered.
 * @author Lucas Bandeira
 */
public class Coffee extends MenuItem {
    /**
     * This is the com.example.project5.Coffee's selected cup size
     */
    private Cupsize size;

    /**
     * This is the com.example.project5.Coffee's list of add-ins selected
     */
    private final List<AddIn> addIns;

    /**
     * This is the base price of a Short, Black com.example.project5.Coffee
     */
    private static final double BASE_PRICE_SHORT_BLACK = 2.39;
    /**
     * The price increment per cup size increase
     */
    private static final double SIZE_INCREMENT = 0.60;
    /**
     * The price for each add-in selected
     */
    private static final double ADDIN_PRICE = 0.25;

    /**
     * Constructor for a com.example.project5.Coffee object with the given attributes
     *
     * @param size     the selected cup size
     * @param addIns   the selected addins
     * @param quantity the numbner of coffees ordered
     */
    public Coffee(Cupsize size, List<AddIn> addIns, int quantity) {
        super(quantity);
        this.size = size;
        this.addIns = new ArrayList<>();
        if (addIns != null) {
            this.addIns.addAll(addIns);
        }
    }

    /**
     * Returns the coffee's cup size
     *
     * @return coffee's cup size
     */
    public Cupsize getSize() {
        return size;
    }

    /**
     * Updates the coffee's cup size
     *
     * @param size updates coffee's cup size
     */
    public void setSize(Cupsize size) {
        this.size = size;
    }

    /**
     * Returns the selected add-ins in the coffee
     *
     * @return coffee's selected add-ins
     */
    public List<AddIn> getAddIns() {
        return new ArrayList<>(addIns);
    }

    /**
     * Updates the current addins with the list
     *
     * @param addIns new add-ins to apply and add
     */
    public void setAddIns(List<AddIn> addIns) { // Updates coffee's selected add-ins
        this.addIns.clear();
        if (addIns != null) {
            this.addIns.addAll(addIns);
        }
    }

    /**
     * Adds a single, available add-in to the coffee
     *
     * @param addIn the add-in for add
     *
     */
    public void addAddIn(AddIn addIn) {
        if (!addIns.contains(addIn)) {
            addIns.add(addIn);
        }
    }

    /**
     * Removes a single add-in from the coffee
     *
     * @param addIn the add-in for remove
     *
     */
    public void removeAddIn(AddIn addIn) {
        addIns.remove(addIn);
    }

    @Override
    public double price()
    {
        double price = BASE_PRICE_SHORT_BLACK;
        switch(size)
        {
            case TALL: price += SIZE_INCREMENT; break;
            case GRANDE: price += SIZE_INCREMENT*2; break;
            case VENTI: price += SIZE_INCREMENT*3; break;
            default: break;
        }
        price += addIns.size() * ADDIN_PRICE;
        return price * quantity;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("com.example.project5.Coffee (");
        sb.append(size);
        if (!addIns.isEmpty()) {
            sb.append(" | add-ins: ");
            for (int i = 0; i < addIns.size(); i++) {
                sb.append(addIns.get(i));
                if (i < addIns.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(") x").append(quantity);
        sb.append(String.format(" - $%.2f", price()));
        return sb.toString();
    }

}
