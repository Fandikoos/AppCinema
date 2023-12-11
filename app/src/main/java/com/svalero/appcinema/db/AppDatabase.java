package com.svalero.appcinema.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.util.Converters;

@Database(entities = {Cinema.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CinemaDao cinemaDao();
}
