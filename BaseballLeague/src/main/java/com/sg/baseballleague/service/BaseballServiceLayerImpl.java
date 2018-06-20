package com.sg.baseballleague.service;

import com.sg.baseballleague.dao.BaseballPlayerDao;
import com.sg.baseballleague.dao.BaseballTeamDao;

public class BaseballServiceLayerImpl implements BaseballServiceLayer {

    //properties
    private BaseballPlayerDao playerDao;
    private BaseballTeamDao teamDao;

    //constructor
    public BaseballServiceLayerImpl(BaseballPlayerDao playerDao, BaseballTeamDao teamDao) {
        this.playerDao = playerDao;
        this.teamDao = teamDao;
    }
}
