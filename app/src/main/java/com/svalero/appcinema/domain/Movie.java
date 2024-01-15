package com.svalero.appcinema.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String director;
    @ColumnInfo
    private String genre;
    @ColumnInfo
    private int duration;
    @ColumnInfo
    private boolean isFav;
    @ColumnInfo
    private String description;
    /*@ColumnInfo
    private @NonNull LocalDate releaseDate;*/

    public Movie(String title, String director, String genre, int duration/*, LocalDate releaseDate*/){
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        //this.releaseDate = releaseDate;
    }

    @Ignore
    public Movie(long id, String title, String director, String genre, int duration){
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
    }

    @Ignore
    public Movie(String title, String director, String description){
        this.title = title;
        this.director = director;
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }*/

}
