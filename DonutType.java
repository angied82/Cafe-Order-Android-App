package com.example.project5;

/**
 * Enum with all donut types and the price
 * @author Angela Ding and Lucas Bandeira
 */
public enum DonutType
{
    YEAST, CAKE, HOLE, SEASONAL;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
