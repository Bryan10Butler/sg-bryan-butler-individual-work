package com.sg.baseballleague.dto;

import java.util.Objects;

public class Team {

    private int teamId;
    private String teamName;
    private String leagueName;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getTeamId() == team.getTeamId() &&
                Objects.equals(getTeamName(), team.getTeamName()) &&
                Objects.equals(getLeagueName(), team.getLeagueName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTeamId(), getTeamName(), getLeagueName());
    }
}
