package com.example.project5;

/**
 * Enum with all sandwiches add-ons and the prices
 * @author Angela Ding and Lucas Bandeira
 */
public enum AddOn
{
    LETTUCE, TOMATO, ONION, CHEESE;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
