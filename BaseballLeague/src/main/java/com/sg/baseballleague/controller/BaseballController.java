package com.sg.baseballleague.controller;

import com.sg.baseballleague.dto.Team;
import com.sg.baseballleague.service.BaseballServiceLayer;
import com.sg.baseballleague.ui.BaseballView;

public class BaseballController {

    //properties
    private BaseballServiceLayer myService;
    private BaseballView myView;

    //constructor
    public BaseballController(BaseballServiceLayer myService, BaseballView myView) {
        this.myService = myService;
        this.myView = myView;
    }

    public void run() {

        boolean workToBeDone = true;
        int menuSelection;

        while(workToBeDone) {
            //assign value from printMenuAndRetrieveSelection to menuSelection
            menuSelection = printMenuAndRetrieveSelection();
            switch(menuSelection) {

                case 1:
                    System.out.println("Create new team");
                    break;
                case 2:
                    System.out.println("Create new player");
                    break;
                case 3:
                    System.out.println("List all teams in each league");
                    break;
                case 4:
                    System.out.println("List all players on a team");
                    break;
                case 5:
                    System.out.println("Trade player");
                    break;
                case 6:
                    System.out.println("Delete player");
                    break;
                case 7:
                    System.out.println("Delete team");
                    break;
                case 8:
                    System.out.println("Save");
                    break;
                case 9:
                    workToBeDone = false;
                    break;
                default:
                    System.out.println("Invalid command");
            }//End of Switch
        }//End of While
    }//End of run

    //Retrieve menu selection from the view
    private int printMenuAndRetrieveSelection() {
        return myView.displayMenuAndRetrieveSelection();
    }

    private void createNewTeam() {
        //Retrieve new team information from the view via user input
        Team teamToCreate = myView.promptForNewTeamInfo();

        //Display new team summary to user

        //Prompt to commit

            //True
            //Pass teamToCreate to the service layer
            //Success banner

            //False
            //Trash team banner
    }
}//End of controller
