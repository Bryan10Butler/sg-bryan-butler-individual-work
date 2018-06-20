package com.sg.baseballleague.service;

public class TeamNotFoundException extends Exception {

    public TeamNotFoundException(String message) {
        super(message);
    }

    public TeamNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
