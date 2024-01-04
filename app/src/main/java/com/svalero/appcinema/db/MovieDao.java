package com.svalero.appcinema.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.appcinema.domain.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insertMovie(Movie movie);
    @Delete
    void deleteMovie(Movie movie);
    @Query("SELECT * FROM movie")
    List<Movie> getFavoriteMovies();
    @Query("SELECT * FROM movie WHERE id = :id")
    Movie findMovieById(long id);

}
