package com.example.demo.foodOrder.logic;

public class BestellungsException extends RuntimeException{
    /**
     * dies Exception wird geworfen wenn in der Klasse bestellung eine nicht gewünschter Fall
     * Abgefangen wird.
     * @param message string der beim Throw der Exception als Nachricht ausgegeben wird
     */
    public BestellungsException(String message) {
        super(message);
    }
}
