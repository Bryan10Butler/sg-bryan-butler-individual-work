package com.sg.service;

import com.sg.dao.PlayerDao;
import com.sg.dto.Player;
import com.sg.dto.Position;
import com.sg.dto.Team;

import javax.inject.Inject;
import java.util.List;

public class PlayerServiceImpl implements PlayerService{

    PlayerDao playerDao;

    @Inject
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Player create(Player Player) {
        return playerDao.create(Player);
    }

    @Override
    public Player read(Long id) {
        return playerDao.read(id);
    }

    @Override
    public void update(Player Player) {
        playerDao.update(Player);

    }

    @Override
    public void delete(Player Player) {
        playerDao.delete(Player);
    }

    @Override
    public List<Player> getPlayersByTeam(Team team, int limit, int offset) {
        return playerDao.getPlayersByTeam(team, limit, offset);
    }

    @Override
    public List<Player> getPlayerByPosition(Position position, int limit, int offset) {
        return playerDao.getPlayerByPosition(position, limit, offset);
    }

    @Override
    public void addPlayerToPosition(Player player, Position position) {

    }

    @Override
    public void deletePlayerFromPosition(Player player, Position position) {

    }

    @Override
    public List<Player> list(int limit, int offset) {
        return playerDao.list(limit, offset);
    }
}
