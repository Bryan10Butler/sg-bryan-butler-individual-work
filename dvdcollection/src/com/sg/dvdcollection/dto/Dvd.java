package com.sg.dvdcollection.dto;

public class Dvd {

    private String title;
    private String releaseDate;
    private String ratingMppa;
    private String directorName;
    private String studio;
    private String userRating;

    //constructor
    public Dvd(String title, String releaseDate, String ratingMppa,
               String directorName, String studio, String userRating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.ratingMppa = ratingMppa;
        this.directorName = directorName;
        this.studio = studio;
        this.userRating = userRating;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRatingMppa() {
        return ratingMppa;
    }

    public void setRatingMppa(String ratingMppa) {
        this.ratingMppa = ratingMppa;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}
