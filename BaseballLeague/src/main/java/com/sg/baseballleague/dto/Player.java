package com.sg.baseballleague.dto;

import java.util.Objects;

public class Player {

    private int playerNumber;
    private String firstName;
    private String lastName;
    private int teamId;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getPlayerNumber() == player.getPlayerNumber() &&
                getTeamId() == player.getTeamId() &&
                Objects.equals(getFirstName(), player.getFirstName()) &&
                Objects.equals(getLastName(), player.getLastName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPlayerNumber(), getFirstName(), getLastName(), getTeamId());
    }
}
