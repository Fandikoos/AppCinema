package com.svalero.appcinema.view;

import static com.svalero.appcinema.util.Constants.DATABASE_NAME;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.appcinema.R;
import com.svalero.appcinema.adapter.FavoriteMovieAdapter;
import com.svalero.appcinema.adapter.MovieAdapter;
import com.svalero.appcinema.db.AppDatabase;
import com.svalero.appcinema.db.MovieDao;
import com.svalero.appcinema.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesView extends AppCompatActivity {

    private AppDatabase db;

    private List<Movie> favoriteMovies;

    private FavoriteMovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        favoriteMovies = new ArrayList<>();
        db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        MovieDao movieDao = db.movieDao();
        favoriteMovies = movieDao.getFavoriteMovies();


        RecyclerView recyclerView = findViewById(R.id.favoriteMoviesRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Log.i("Movie", String.valueOf(favoriteMovies.size()));
        adapter = new FavoriteMovieAdapter(favoriteMovies);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
