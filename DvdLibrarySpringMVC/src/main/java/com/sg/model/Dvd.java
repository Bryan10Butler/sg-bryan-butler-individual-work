package com.sg.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

public class Dvd {
    private long dvdId;
    private String title;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate releaseLocalDate;
    private String ratingMppa;
    private String directorName;
    private String studio;
    private String userRating;
    private String notes;


    public long getDvdId() {
        return dvdId;
    }

    public void setDvdId(long dvdId) {
        this.dvdId = dvdId;
    }

    public LocalDate getReleaseLocalDate() {
        return releaseLocalDate;
    }

    public void setReleaseLocalDate(LocalDate releaseLocalDate) {
        this.releaseLocalDate = releaseLocalDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                Objects.equals(getReleaseLocalDate(), dvd.getReleaseLocalDate()) &&
                Objects.equals(getRatingMppa(), dvd.getRatingMppa()) &&
                Objects.equals(getDirectorName(), dvd.getDirectorName()) &&
                Objects.equals(getStudio(), dvd.getStudio()) &&
                Objects.equals(getUserRating(), dvd.getUserRating()) &&
                Objects.equals(getNotes(), dvd.getNotes());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTitle(), getReleaseLocalDate(), getRatingMppa(), getDirectorName(), getStudio(), getUserRating(), getNotes());
    }
}
