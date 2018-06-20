package com.sg.dao;

import com.sg.dto.Player;
import com.sg.dto.Position;
import com.sg.dto.Team;

import java.util.List;


public interface PlayerDao {

    public Player create(Player Player);
    public Player read(Long id);
    public void update(Player Player);
    public void delete(Player Player);
    //offset how many you are going to skip
    //limit is how many you show
    //one to many
    public List<Player> getPlayersByTeam(Team team, int limit, int offset);


    //many to many
    public List<Player> getPlayerByPosition(Position position, int limit, int offset);

    public List<Player> list(int limit, int offset);

}
