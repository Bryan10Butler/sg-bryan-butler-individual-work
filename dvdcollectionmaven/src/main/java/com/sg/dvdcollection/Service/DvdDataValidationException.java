package com.sg.dvdcollection.Service;

public class DvdDataValidationException extends Exception {

    public DvdDataValidationException(String message) {
        super(message);
    }
    public DvdDataValidationException(String message,
                                      Throwable cause) {
        super(message, cause);
    }

}
