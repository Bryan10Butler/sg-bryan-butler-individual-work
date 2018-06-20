//package com.sg.dao;
//
//import com.sg.model.Dvd;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class DvdDaoInMemoryImpl implements DvdDao{
//
//    private Map<String,Dvd> dvdMap = new HashMap<>();
//
//    @Override
//    public Dvd getDvd(String dvdTitle) {
//        return dvdMap.get(dvdTitle);
//    }
//
//    @Override
//    public List<Dvd> getAllDvds() {
//        return new ArrayList<Dvd>(dvdMap.values());
//    }
//
//    @Override
//    public Dvd removeDvd(String dvdTitle) {
//        Dvd removeDvd = dvdMap.remove((dvdTitle));
//        return removeDvd;
//    }
//
//    @Override
//    //Put supplied Dvd information into our map
//    public Dvd addDvd(Dvd dvd) {
//        Dvd newDvd = dvdMap.put(dvd);
//        return newDvd;
//    }
//}