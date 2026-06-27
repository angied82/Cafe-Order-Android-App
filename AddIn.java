package com.example.project5;

/**
 * Enum with all the coffee add-ons all cost the same price
 * @author Angela Ding and Lucas Bandeira
 */
public enum AddIn
{
    WHIPPED_CREAM, VANILLA, TWO_PERCENT_MILK, CARAMEL, MOCHA;

    @Override
    public String toString() {
        String name = name().toLowerCase().replace('_',' ');
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);

    }
}
