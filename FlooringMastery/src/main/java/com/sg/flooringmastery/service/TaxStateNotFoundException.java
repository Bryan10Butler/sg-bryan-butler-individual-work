package com.sg.flooringmastery.service;

public class TaxStateNotFoundException extends Exception {

    public TaxStateNotFoundException(String message) {
        super(message);
    }

    public TaxStateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
