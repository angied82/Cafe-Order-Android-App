package com.example.project5;

/**
 *This class represents a donut menu item.
 *  * Each com.example.project5.Donut menu item has a flavor, which determines the donut type, and quantity.
 *  * Pricing is determined and calc'd by type as:
 *  *  - yeast donuts
 *  *  - cake donuts
 *  *  - donut holes
 *  *  - and seasonal donuts
 *  * The final cost is the unit price by the number ordered
 * @author Lucas Bandeira
 */
public class Donut extends MenuItem {

    /**  The selected flavor of the donut*/
    private DonutFlavor flavor;

    /** Prices of each type of donut: yeast, cake, donut holes, and seasonal*/
    private static final double YEAST_PRICE = 1.99;
    private static final double CAKE_PRICE = 2.19;
    private static final double HOLE_PRICE = 0.39;
    private static final double SEASONAL_PRICE = 2.49;


    /** Constructor for a donut with the given flavor and quantity as param.*/
    public Donut(DonutFlavor flavor, int quantity) {
        super(quantity);
        this.flavor = flavor;
    }

    /** Returns the unit price of the donut based on its type */
    private double unitPrice(){
        DonutType type = flavor.getType();
        switch (type) {
            case YEAST:
                return YEAST_PRICE;
            case CAKE:
                return CAKE_PRICE;
            case HOLE:
                return HOLE_PRICE;
            case SEASONAL:
                return SEASONAL_PRICE;
            default:
                return 0.0;
        }
    }


    /** Returns the flavor of the donut*/
    public DonutFlavor getFlavor() {
        return flavor;
    }

    /** Updates the flavor of the donut */
    public void setFlavor(DonutFlavor flavor) {
        this.flavor = flavor;
    }

    /** Calculates the total price of the donut order by the unitPrice of the donut
     * multipled by the quantity ordered.
     *
     * @return the total final price*/
    @Override
    public double price() {
        return unitPrice() * quantity;
    }

    /**Represents the donut and its attributes as a string.
     * @return string description of the donut order
     * */
    @Override
    public String toString() {
        return "com.example.project5.Donut (" + flavor + ") x" + quantity + String.format(" - $%.2f", price());
    }


}
