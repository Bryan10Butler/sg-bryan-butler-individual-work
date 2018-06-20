package com.sg.dvdcollection.Service;

import com.sg.dvdcollection.dao.DvdPersistenceException;
import com.sg.dvdcollection.dto.Dvd;

import java.util.List;

public interface DvdServiceLayer {

        void createDvd(Dvd dvd) throws
                DvdDuplicateTitleException,
                DvdDataValidationException,
                DvdPersistenceException;

        List<Dvd> getAllDvds() throws
                DvdPersistenceException;

        Dvd getDvd(String DvdTitle) throws
                DvdPersistenceException;

        Dvd removeDvd(String dvdTitle) throws
                DvdPersistenceException;

        Dvd addDvd(String dvdTitle, Dvd dvd) throws
                DvdPersistenceException;

    }

