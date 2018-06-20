package com.sg.baseballleague.dao;

public class BaseballPersistenceException extends Exception {

    public BaseballPersistenceException(String message) {
        super(message);
    }

    public BaseballPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
