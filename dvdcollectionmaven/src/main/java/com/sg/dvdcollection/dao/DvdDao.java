package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Dvd;

import java.util.List;

//handle retrieving and storing data
public interface DvdDao {

    Dvd getDvd(String dvdTitle)
        throws DvdPersistenceException;

    List<Dvd> getAllDvds()
        throws DvdPersistenceException;

    Dvd removeDvd(String dvdTitle)
        throws DvdPersistenceException;

    //understand this Dvd dvd
    Dvd addDvd(String dvdTitle, Dvd dvd)
        throws DvdPersistenceException;

    void writeCollection()
        throws DvdPersistenceException;

    void  loadCollection()
        throws DvdPersistenceException;

}//end of interface DvdDao
