package com.example.project5;

/**
 * Enum with all the protein options for the sandwiches and the price
 * @author Angela Ding and Lucas Bandeira
 */
public enum Protein
{
    BEEF, CHICKEN, SALMON;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
