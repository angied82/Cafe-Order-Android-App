package com.example.project5;

/**
 *Enum with all the different flavors for each type of donut
 * @author Lucas Bandeira
 */

public enum DonutFlavor {
    YEAST_GLAZED(DonutType.YEAST),
    YEAST_CHOCOLATE(DonutType.YEAST),
    YEAST_STRAWBERRY(DonutType.YEAST),
    YEAST_CREAM(DonutType.YEAST),
    YEAST_JELLY(DonutType.YEAST),
    YEAST_SPRINKLES(DonutType.YEAST),

    CAKE_PLAIN(DonutType.CAKE),
    CAKE_CHOCOLATE(DonutType.CAKE),
    CAKE_STRAWBERRY(DonutType.CAKE),

    HOLE_SUGAR(DonutType.HOLE),
    HOLE_CINNAMON(DonutType.HOLE),
    HOLE_POWDERED(DonutType.HOLE),

    SPOOKY(DonutType.SEASONAL),
    PUMPKIN_SPICE(DonutType.SEASONAL);

    private final DonutType type;

    DonutFlavor(DonutType type) {
        this.type = type;
    }

    public DonutType getType() {
        return type;
    }

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}