package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdDaoFileImpl implements DvdDao {

    public static final String DVD_FILE = "DVDs.txt";
    public static final String DELIMITER = "::";

    //Create map to look up Dvd
    //Is my Key based off constructor?
    private Map<String,Dvd> dvdMap = new HashMap<>();


    @Override
    public Dvd getDvd(String dvdTitle)
    throws DvdDaoException{
        loadCollection();
        return dvdMap.get(dvdTitle);
    }

    @Override
    public List<Dvd> getAllDvds()
    throws DvdDaoException{
        loadCollection();
        return new ArrayList<Dvd>(dvdMap.values());
    }

    @Override
    public Dvd removeDvd(String dvdTitle)
    throws DvdDaoException{
        Dvd removeDvd = dvdMap.remove((dvdTitle));
        writeCollection();
        return removeDvd;
    }

    @Override
    //Put supplied Dvd information into our map
    public Dvd addDvd(String dvdTitle, Dvd dvd)
    throws DvdDaoException{
        Dvd newDvd = dvdMap.put(dvdTitle, dvd);
        writeCollection();
        return newDvd;
    }

    @Override
    public void loadCollection() throws DvdDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        // currentLine looks like this:
        // 1234::Joe::Smith::Java-September2013
        // then currentTokens will be a string array that looks like this:
        //
        // ___________________________________
        // |    |   |     |                  |
        // |1234|Joe|Smith|Java-September2013|
        // |    |   |     |                  |
        // -----------------------------------
        //  [0]  [1]  [2]         [3]
        String[] currentTokens;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Student object and put it into the map of students
            // NOTE FOR APPRENTICES: We are going to use the student id
            // which is currentTokens[0] as the map key for our student object.
            // We also have to pass the student id into the Student constructor
            Dvd dvdCollection = new Dvd(currentTokens[0], currentTokens[1], currentTokens [2], currentTokens [3], currentTokens [4], currentTokens [5]);
            // Set the remaining values on currentStudent manually
            dvdCollection.setTitle(currentTokens[0]);
            dvdCollection.setDirectorName(currentTokens[1]);
            dvdCollection.setRatingMppa(currentTokens[2]);
            dvdCollection.setReleaseDate(currentTokens[3]);
            dvdCollection.setStudio(currentTokens[4]);
            dvdCollection.setUserRating(currentTokens[5]);
            // Put currentStudent into the map using studentID as the key
            dvdMap.put(dvdCollection.getTitle(), dvdCollection);
        }
        // close scanner
        scanner.close();
    }

    @Override
    public void writeCollection() throws DvdDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdDaoException(
                    "Could not save Dvd data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        List<Dvd> studentList = this.getAllDvds();
        for (Dvd currentStudent : studentList) {
            // write the Student object to the file
            out.println(currentStudent.getTitle() + DELIMITER
                    + currentStudent.getDirectorName() + DELIMITER
                    + currentStudent.getRatingMppa() + DELIMITER
                    + currentStudent.getReleaseDate() + DELIMITER
                    + currentStudent.getStudio() + DELIMITER
                    + currentStudent.getUserRating());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
