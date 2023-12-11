package com.svalero.appcinema.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.appcinema.domain.Cinema;

import java.util.List;

@Dao
public interface CinemaDao {

    @Query("SELECT * FROM cinema")
    List<Cinema> getAll();

    @Insert
    void insert(Cinema cinema);

    @Update
    void update(Cinema cinema);

    @Delete
    void delete(Cinema cinema);
}
