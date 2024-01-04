package com.svalero.appcinema.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.appcinema.domain.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
