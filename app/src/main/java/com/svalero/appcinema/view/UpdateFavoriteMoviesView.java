package com.svalero.appcinema.view;

import static com.svalero.appcinema.util.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.appcinema.R;
import com.svalero.appcinema.db.AppDatabase;
import com.svalero.appcinema.domain.Movie;

public class UpdateFavoriteMoviesView extends AppCompatActivity {

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_favorite_movie);

        db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().addMigrations(AppDatabase.MIGRATION_1_2).build();

        Intent intent = getIntent();
        String movieDescription = intent.getStringExtra("movieDescription");

        TextView tvDescription = findViewById(R.id.getTextViewMovieDescription);

        if (movieDescription != null) {
            tvDescription.setText(getString(R.string.actualDescription).concat(movieDescription));
        }

        tvDescription.setText(movieDescription);

    }

    public void updateFavoriteMovie (View view) {
        EditText etDescription = findViewById(R.id.editTextMovieDescription);
        Intent intent = getIntent();
        long movieId = intent.getLongExtra("movieId", 0);
        String movieTitle = intent.getStringExtra("movieTitle");
        String movieDirector = intent.getStringExtra("movieDirector");

        String movieDescription = etDescription.getText().toString();

        Log.d("movie id", String.valueOf(movieId));
        Movie updateFavMovie = new Movie(movieTitle, movieDirector, movieDescription);
        updateFavMovie.setId(movieId);

        db.movieDao().updateFavMovie(updateFavMovie);
        showSuccessSnackbar(view, R.string.updateFavMovie);
    }

    private void showSuccessSnackbar(View view, int message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.setAction(R.string.acept, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en el botón de acción (puedes dejar esto vacío si no necesitas ninguna acción)
            }
        });
        snackbar.show();
    }
}
