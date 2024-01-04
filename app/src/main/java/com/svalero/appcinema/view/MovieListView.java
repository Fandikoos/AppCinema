package com.svalero.appcinema.view;

import static com.svalero.appcinema.util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.appcinema.R;
import com.svalero.appcinema.adapter.MovieAdapter;
import com.svalero.appcinema.contract.MovieListContract;
import com.svalero.appcinema.db.AppDatabase;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.presenter.MovieListPresenter;

import java.util.ArrayList;
import java.util.List;

public class MovieListView extends AppCompatActivity implements MovieListContract.View {

    private AppDatabase db;
    private List<Movie> movies;
    private MovieAdapter adapter;
    private MovieListContract.Presenter presenter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();

        presenter = new MovieListPresenter(this);

        movies = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.movie_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(movies, db);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

        presenter.loadAllMovies();
    }

    public void addMovies(View view){
        goToAddMovie();
    }

    public void goToCinemas(View view){
        goToViewCinemas();
    }

    public void goToFavoriteMovies(View view){
        viewFavoritesMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.action_bar_movies, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_movie) {
            goToAddMovie();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToAddMovie(){
        Intent intent = new Intent(this, RegisterMovieView.class);
        startActivity(intent);
    }

    public void goToViewCinemas(){
        Intent intent = new Intent(this, CinemaListView.class);
        startActivity(intent);
    }

    public void viewFavoritesMovies(){
        Intent intent = new Intent(this, FavoriteMoviesView.class);
        startActivity(intent);
    }

    @Override
    public void listMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
