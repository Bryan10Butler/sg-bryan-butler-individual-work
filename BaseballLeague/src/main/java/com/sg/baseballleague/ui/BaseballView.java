package com.sg.baseballleague.ui;

import com.sg.baseballleague.dto.Team;

public class BaseballView {

    //properties
    private UserIO myIo;

    //constructor
    public BaseballView(UserIO myIo) {
        this.myIo = myIo;
    }

    public int displayMenuAndRetrieveSelection() {
        //Display the menu
        myIo.print("1: Create new team");
        myIo.print("2: Create new player");
        myIo.print("3: List all teams in each league");
        myIo.print("4: List all players from a team");
        myIo.print("5: Trade player");
        myIo.print("6: Remove player");
        myIo.print("7: Remove team");
        myIo.print("8: Call it a day");
        myIo.print("9: Quit");

        //Capture user response
        return myIo.readInt("====Please select from the above options====", 1,9);
    }

    public Team promptForNewTeamInfo() {
        //Prompt for new team information
        String teamName = myIo.readString("====Please provide a team name====");
        String leagueName = myIo.readString("====Please provide the league name====");

        //Create new team object
        Team newTeam = new Team();

        //Assign value to newTeam
        newTeam.setTeamName(teamName);
        newTeam.setLeagueName(leagueName);

        //return new team object
        return newTeam;
    }
}
