package com.svalero.appcinema.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.svalero.appcinema.util.Converters;

@Entity
@TypeConverters(Converters.class)
public class Cinema {

    @PrimaryKey
    private @NonNull String name;
    @ColumnInfo
    private int capacity;
    /*
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;*/
    @ColumnInfo
    private float rating;
    //@ColumnInfo(name = "opening_date")
    //private LocalDate openingDate;

    public Cinema(String name, int capacity, float rating){
        this.name = name;
        this.capacity = capacity;
        /*
        this.latitude = latitude;
        this.longitude = longitude;*/
        this.rating = rating;
        //this.openingDate = openingDate;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
/*
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }*/

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    /*
    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }*/
}
