package com.sg.dvdcollection.Service;

public class DvdDuplicateTitleException extends Exception{

    public DvdDuplicateTitleException(String message) {
        super(message);
    }
    public DvdDuplicateTitleException(String message,
                                      Throwable cause) {
        super(message, cause);
    }
}
