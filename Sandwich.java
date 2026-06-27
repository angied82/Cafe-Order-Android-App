package com.example.project5;

import java.util.ArrayList;
import java.util.List;
/**
 *This class represents a sandwich menu items.
 *  * Each com.example.project5.Sandwich menu item has a bread type, protein choice, add-ons, and quanity.
 *  * Using these attributes, the price is determined and calc'd by:
 *  *  - Base price for the selected protein
 *  *  - A fixed added price per add-in
 *  *  - And the number of sandwich items ordered.
 * @author Lucas Bandeira
 */

public class Sandwich extends MenuItem {

    /** The com.example.project5.Sandwich's type of bread*/
    protected Bread bread;
    /** the com.example.project5.Sandwich's type of protein*/
    protected Protein protein;
    /** The com.example.project5.Sandwich's list of add-ons selected */
    protected List<AddOn> addOns;

    /** The base price of a beef sandwich*/
    private static final double BEEF_BASE = 12.99;
    /** The base price of a chicken sandwich*/
    private static final double CHICKEN_BASE = 10.99;
    /** The base price of a salmon base*/
    private static final double SALMON_BASE = 14.99;

    /** The price increment per add-on (excluding cheese)*/
    private static final double ADDON_LETTUCE_TOMATO_ONION_PRICE = 0.30;
    /** The price for cheese add-on*/
    private static final double ADDON_CHEESE_PRICE = 1.00;


    /** Constructor for a new com.example.project5.Sandwich given the bread, protein, add-ons, and quant
     *
     * @param bread - the type of bread of the sandwich
     * @param protein - the type of protein of the sandwich
     * @param addOns  - the list of add-ons in the sandwich
     * @param quantity - the number of sandwich orders
     * */
    public Sandwich(Bread bread, Protein protein, List<AddOn> addOns, int quantity){
        super(quantity);
        this.bread = bread;
        this.protein = protein;
        this.addOns = new ArrayList<>();
        if (addOns != null)
            this.addOns.addAll(addOns);
    }

    /** Returns the sandwich's type of bread
     * @return sandwich's type of bread*/
    public Bread getBread() {
        return bread;
    }

    /** Updates the sandwich's type of bread
     * @param bread the new type of bread to set to*/
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    /** Returns the sandwich's type of protein
     * @return sandwich's type of protein*/
    public Protein getProtein() {
        return protein;
    }

    /** Updates the sandwich's type of protein
     * @param protein the new type of protein to set to*/
    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    /** Returns the selected add-ins in the sandwich
     * @return sandwich's selected add-ons*/
    public List<AddOn> getAddOns() {
        return new ArrayList<>(addOns);
    }

    /** Updates the current addons with the list
     * @param addOns new add-ons to apply and add*/
    public void setAddOns(List<AddOn> addOns) {
        this.addOns.clear();
        if (addOns != null) {
            this.addOns.addAll(addOns);
        }
    }

    /** Adds a single, available add-on to the sandwich
     * @param addOn the add-on for add
     * */
    public void addAddOn(AddOn addOn) {
        if (!addOns.contains(addOn)){
            addOns.add(addOn);
        }
    }

    /** Removes a single add-on from the sandwich
     * @param addOn the add-on for remove
     * */
    public void removeAddOn(AddOn addOn) {
        addOns.remove(addOn);
    }

    /** Returns the base price of the sandwich according to protein
     * @return base price calculated */
    private double basePriceByProtein() {
        switch (protein) {
            case CHICKEN:
                return CHICKEN_BASE;
            case SALMON:
                return SALMON_BASE;
            case BEEF:
            default:
                return BEEF_BASE;
        }
    }

    /** Return the total price of all selected add ons.
     * @return total price of addons*/
    private double addOnsPrice() {
        double total = 0.0;
        for (AddOn addOn: addOns) {
            switch (addOn) {
                case LETTUCE:
                case TOMATO:
                case ONION:
                    total += ADDON_LETTUCE_TOMATO_ONION_PRICE;
                    break;
                case CHEESE:
                    total += ADDON_CHEESE_PRICE;
                    break;
                default:
                    break;
            }
        }
        return total;
    }


    /**
     * Calculates the total price of the sandwich order.
     *
     * The price per sandwich is calculated by:
     * - Base price of protein
     * - Adds the cost of number of selected add-ins
     * - Then multiplied by the quantity
     *
     * @return the total final price
     */
    @Override
    public double price() {
        double perSandwich = basePriceByProtein() + addOnsPrice();
        return perSandwich * quantity;
    }

    /**Represents the sandwich and its attributes as a string.
     * @return string description of the sandwich order
     * */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("com.example.project5.Sandwich (");
        sb.append(bread).append(", ").append(protein);
        if (!addOns.isEmpty()) {
            sb.append(" | add-ons: ");
            for (int i = 0; i < addOns.size(); i++){
                sb.append(addOns.get(i));
                if (i < addOns.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(") x").append(quantity);
        sb.append(String.format(" - $%.2f", price()));
        return sb.toString();
    }

}