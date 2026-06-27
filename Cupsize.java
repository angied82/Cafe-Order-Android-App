package com.example.project5;

/**
 * Enum with all the cup sizes for coffee and the price
 * @author Angela Ding and Lucas Bandeira
 */
public enum Cupsize
{
    SHORT, TALL, GRANDE, VENTI;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
