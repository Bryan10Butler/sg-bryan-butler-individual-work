package com.sg.service;

public class VendingMachineItemUnavailableException extends Exception{

    public VendingMachineItemUnavailableException(String message) {
        super(message);
    }

    public VendingMachineItemUnavailableException(String message, Throwable cause) {
        super(message);
    }
}


