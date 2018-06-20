package com.sg.dao;

import com.sg.model.Dvd;

import java.util.List;

public interface DvdDao {

    Dvd getDvd(Long DvdId);

    List<Dvd> getAllDvds();

    void removeDvd(Long dvdId);

    //understand this Dvd dvd
    Dvd addDvd(Dvd dvd);

    void editDvd(Dvd dvd);
}
