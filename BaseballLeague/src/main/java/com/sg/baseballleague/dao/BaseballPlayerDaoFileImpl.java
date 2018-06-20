package com.sg.baseballleague.dao;

public class BaseballPlayerDaoFileImpl implements BaseballPlayerDao {

    private static String playerFile;
    private static final String DELIMETER = "::";

    public BaseballPlayerDaoFileImpl(String FILENAME) {
        this.playerFile = FILENAME;
    }
}
