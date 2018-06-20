package com.sg.dvdcollection.ui;

import com.sg.dvdcollection.dao.DvdDaoException;
import com.sg.dvdcollection.dto.Dvd;

import java.util.List;

public class DvdView {

    private UserIO io;

    public DvdView(UserIO io) {
        this.io = io;
    }

    //Main menu selection for user. Evaluate selection (int) from user.
    public int printMenuAngGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit existing DVD");
        io.print("4. List all DVDs");
        io.print("5. Search for DVD by DVD Title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.",1,6);

    }//end of print menu and get selection method

    public Dvd getNewDvdInfo() {

        String title = io.readString("Please enter the DVD Title");
        String releaseDate = io.readString("Please enter the DVD Release Data");
        String mppaRating = io.readString("Please enter the DVD MPPA Rating");
        String directorsName = io.readString("Please enter the Director's Name");
        String studio = io.readString("Please enter the Studio");
        String userRating = io.readString("Please enter the User Rating");

        //We don't need to list setters because we are using our constructor to pass in
        Dvd currentDvd = new Dvd(title, releaseDate, mppaRating, directorsName, studio, userRating);
        return currentDvd;
    }//end of get new dvd info

    public void displayCreateDvdBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }


    public void displaydvdlist(List<Dvd> dvdList) {

        //enhanced for loop
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getTitle());

        }//end of for
        io.readString("Please hit enter to continue");


    }//end of display dvd list

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVD Titles ===");
    }

    public void displayDisplayDvdBanner () {
        io.print("=== Display DVD Title ===");
    }

    public String getDvdIdChoice() {
        return io.readString("Please enter a DVD Title.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDirectorName());
            io.print(dvd.getRatingMppa());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
        }else {
            io.print("No such DVD");
        }//end of else
        io.readString("Please hit enter to continue");
    }//end of display dvd

    public void displayRemoveDvdBanner () {

        io.print("=== Remove Student ===");

    }//end of remove student banner

    public void displayRemoveSuccessBanenr() {
        io.readString("Dvd successfully removed. Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }

    //Display menu options for editing and prompt user for input
    public void printMenuEditSelection(Dvd dvd) throws DvdDaoException {
        boolean exitEditMenu = true;
        int editMenuSelection;
        while (exitEditMenu) {
            io.print("1. Edit DVD Title");
            io.print("2. Edit Director's Name");
            io.print("3. Edit MPPA Rating");
            io.print("4. Edit Release Date");
            io.print("5. Edit Studio Information");
            io.print("6. Edit User Rating");
            io.print("7. Exit");

            editMenuSelection = io.readInt("Please select from the above choices.", 1, 7);

            //Switch statement to evaluate user input for what he/she wants to edit
            //Pull current item to show current item
            //set current item to new item
            switch (editMenuSelection) {
                case 1:
                    if (dvd != null) {
                        String title = io.readString("Set new Title: " + dvd.getTitle());
                        dvd.setTitle(title);
                        break;
                    }
                case 2:
                    if (dvd != null) {
                        String director = io.readString("Set new Director Name: " + dvd.getDirectorName());
                        dvd.setDirectorName(director);
                        break;
                    }
                case 3:
                    if (dvd != null) {
                        String mppa = io.readString ("Set new MPPA Rating: " + dvd.getRatingMppa());
                        dvd.setRatingMppa(mppa);
                        break;
                    }
                case 4:
                    if (dvd != null) {
                        String releaseDate= io.readString("Set new Release Date: " + dvd.getReleaseDate());
                        dvd.setReleaseDate(releaseDate);
                        break;
                    }
                case 5:
                    if (dvd != null) {
                        String getStudio = io.readString("Set new Studio: " + dvd.getStudio());
                        dvd.setStudio(getStudio);

                    }
                    break;
                case 6:
                    if (dvd != null) {
                        String getUserRating = io.readString("Set new User Rating: " + dvd.getUserRating());
                        dvd.setUserRating(getUserRating);
                        break;
                    }
                case 7:
                        exitEditMenu = false;
                        break;
                default:
                    io.print("Unknown Command");
            }//end of switch
        }
        io.print("Good Bye!");
    }//end of print menu and get selection method

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}//end of class dvd view
