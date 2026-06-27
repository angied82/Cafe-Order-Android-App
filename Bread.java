package com.example.project5;

/**
 * Enum with all the bread options for the sandwiches
 * @author Angela Ding and Lucas Bandeira
 */

public enum Bread
{
    BAGEL, SOURDOUGH, WHEAT;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
