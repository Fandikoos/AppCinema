package com.svalero.appcinema.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.svalero.appcinema.domain.Movie;

@Database(entities = {Movie.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE movie ADD COLUMN description TEXT");
        }
    };
}
