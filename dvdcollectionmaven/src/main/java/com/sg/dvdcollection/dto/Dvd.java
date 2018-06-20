package com.sg.dvdcollection.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Dvd {

    private String title;
    private String releaseDate;
    private LocalDate releaseLocalDate;
    private String ratingMppa;
    private String directorName;
    private String studio;
    private String userRating;

    //constructor
    public Dvd(String title, LocalDate releaseLocalDate, String ratingMppa,
               String directorName, String studio, String userRating) {
        this.title = title;
        this.releaseLocalDate = releaseLocalDate;
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

    public LocalDate getLocalDateReleaseDate() {
        return releaseLocalDate;
    }

    public void setLocalDateReleaseDate(LocalDate localDateReleaseDate) {
        this.releaseLocalDate = localDateReleaseDate;
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

    @Override
    public String toString() {

        return "Title: " + title + "|Release Date: " + releaseLocalDate +
                "|MPPA Rating: " + ratingMppa + "|Director: " +
                directorName + "|Studio: " + studio +
                "|User Rating: " + userRating;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dvd)) return false;
        Dvd dvd = (Dvd) o;
        return Objects.equals(getTitle(), dvd.getTitle()) &&
                Objects.equals(getLocalDateReleaseDate(), dvd.getLocalDateReleaseDate()) &&
                Objects.equals(getRatingMppa(), dvd.getRatingMppa()) &&
                Objects.equals(getDirectorName(), dvd.getDirectorName()) &&
                Objects.equals(getStudio(), dvd.getStudio()) &&
                Objects.equals(getUserRating(), dvd.getUserRating());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTitle(), getLocalDateReleaseDate(), getRatingMppa(), getDirectorName(), getStudio(), getUserRating());
    }
}
