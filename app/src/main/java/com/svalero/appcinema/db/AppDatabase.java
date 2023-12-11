package com.svalero.appcinema.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.appcinema.domain.Cinema;

@Database(entities = {Cinema.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CinemaDao cinemaDao();
}
