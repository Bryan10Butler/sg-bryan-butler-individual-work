package com.sg.baseballleague.dao;

public class BaseballTeamDaoFileImpl implements BaseballTeamDao {

    private static String teamFile;
    private static final String DELIMITER = "::";

    public BaseballTeamDaoFileImpl(String FILENAME) {
        this.teamFile = FILENAME;
    }
}
