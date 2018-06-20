package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Dvd;

import java.util.List;

//handle retrieving and storing data
public interface DvdDao {

    Dvd getDvd(String dvdTitle)
        throws DvdDaoException;

    List<Dvd> getAllDvds()
        throws DvdDaoException;

    Dvd removeDvd(String dvdTitle)
        throws DvdDaoException;

    //understand this Dvd dvd
    Dvd addDvd(String dvdTitle, Dvd dvd)
        throws DvdDaoException;

    void writeCollection()
        throws DvdDaoException;

    void  loadCollection()
        throws DvdDaoException;

}//end of interface DvdDao
