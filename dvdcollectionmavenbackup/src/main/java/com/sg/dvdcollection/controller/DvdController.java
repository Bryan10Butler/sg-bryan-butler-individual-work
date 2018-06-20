package com.sg.dvdcollection.controller;

import com.sg.dvdcollection.dao.DvdDao;
import com.sg.dvdcollection.dao.DvdDaoException;
import com.sg.dvdcollection.dto.Dvd;
import com.sg.dvdcollection.ui.DvdView;

import java.util.List;

public class DvdController {
    //create instance view of object after "new"
    DvdView view;
    //create instance dao of object after "new"
    DvdDao dao;
    //Create instance io of object after "new"

    public DvdController(DvdDao dao, DvdView view) {
        this.dao = dao;
        this.view = view;
    }

    //Run method "Brains"
    public void run () {

        boolean keepGoing = true;
        int menuSelection;
        try {

            //while true
            while (keepGoing) {

                //call method getMenuSelection into menuSelection
                menuSelection = getMenuSelection();

                switch (menuSelection) {

                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvdTitle();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    //out exit from loop
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }//end of switch
            }//end of while
            exitMessage();
        }catch (DvdDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }//end of try/catch
    }//end of run

    //create new method and call view to retrieve the logic
    private int getMenuSelection(){
        return view.printMenuAngGetSelection();
    }//end of method getMenu Selection

    //create dvd
    private void createDvd()
    throws DvdDaoException{
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }//end of create dvd

    //list all dvd
    private void listDvdTitle ()
    throws DvdDaoException{
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displaydvdlist(dvdList);
    }//end of list dvd title

    //list individual dvd
    private void viewDvd()
    throws DvdDaoException{
        view.displayDisplayDvdBanner();
        //get dvd title
        String dvdTitle = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }//end of view dvd

    //remove dvd
    private void removeDvd()
    throws DvdDaoException{
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdIdChoice();
        dao.removeDvd(dvdTitle);
        view.displayRemoveDvdBanner();
    }
    //style
    private void unknownCommand() {

        view.displayUnknownCommandBanner();
    }
    //style
    private void exitMessage () {

        view.displayExitBanner();
    }

    //edit dvd
    //I have add and create
    //rename
    private void editDvd()

    throws DvdDaoException{
        //create variable dvdTitle and initialize to what user input
        String dvdTitle = view.getDvdIdChoice();
        //create new dvd instance of DVD
        //pass in dvdTitle (user option) to getDVD method associated to map
        Dvd dvd = dao.getDvd(dvdTitle);
        //Invoke run method passing in dvd
        view.printMenuEditSelection(dvd);
        //Add dvd to map by using Title as key and passing instance dvd
        dao.addDvd(dvd.getTitle(), dvd);
    }
}//end of class DvdController
