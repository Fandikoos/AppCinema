package com.svalero.appcinema.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Movie {

    @PrimaryKey
    private @NonNull String title;
    @ColumnInfo
    private @NonNull String director;
    @ColumnInfo
    private String genre;
    @ColumnInfo
    private int duration;
    /*@ColumnInfo
    private @NonNull LocalDate releaseDate;*/

    public Movie(String title, String director, String genre, int duration/*, LocalDate releaseDate*/){
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        //this.releaseDate = releaseDate;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDirector() {
        return director;
    }

    public void setDirector(@NonNull String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    /*public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }*/

}
