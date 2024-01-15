package com.svalero.appcinema.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appcinema.R;
import com.svalero.appcinema.contract.UpdateMovieContract;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.presenter.UpdateMoviePresenter;

public class UpdateMovieView extends AppCompatActivity implements UpdateMovieContract.View {

    private UpdateMovieContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        //Cargamos los datos de la película para estableces los textsviews
        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra("movieTitle");
        String movieDirector = intent.getStringExtra("movieDirector");
        String movieGenre = intent.getStringExtra("movieGenre");
        int movieDuration = intent.getIntExtra("movieDuration", 0);

        //Recogemos los text views
        TextView tvTitle = findViewById(R.id.getTextViewMovieTitle);
        TextView tvDirector = findViewById(R.id.getTextViewMovieDirector);
        TextView tvGenre = findViewById(R.id.getTextViewMovieGenre);
        TextView tvDuration = findViewById(R.id.getTextViewMovieDuration);

        //Asignamos el valor a cada text view
        tvTitle.setText(getString(R.string.titleUpdate).concat(movieTitle));
        tvDirector.setText(getString(R.string.directorUpdate).concat(movieDirector));
        tvGenre.setText(getString(R.string.genreUpdate).concat(movieGenre));
        tvDuration.setText(getString(R.string.durationUpdate).concat(String.valueOf(movieDuration)));

        presenter = new UpdateMoviePresenter(this);
    }

    public void updateMovie (View view){
        EditText etTitle = findViewById(R.id.editTextMovieTitle);
        EditText etDirector = findViewById(R.id.editTextMovieDirector);
        EditText etGenre = findViewById(R.id.editTextMovieGenre);
        EditText etDuration = findViewById(R.id.editTextMovieDuration);

        Intent intent = getIntent();
        long movieId = intent.getLongExtra("movieId", 0);

        String movieTitle = etTitle.getText().toString();
        String movieDirector = etDirector.getText().toString();
        String movieGenre = etGenre.getText().toString();
        int movieDuration = Integer.parseInt(etDuration.getText().toString());

        Movie updateMovie = new Movie(movieTitle, movieDirector, movieGenre, movieDuration);
        presenter.updateMovie(movieId, updateMovie);
    }

    @Override
    public void showSuccessMessage(int message) {
        Toast.makeText(this, getResources().getString(message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(int message) {
        Toast.makeText(this, getResources().getString(message), Toast.LENGTH_LONG).show();
    }

    public void goBackToListMovies(View itemView){
        Intent intent = new Intent(itemView.getContext(), MovieListView.class);
        itemView.getContext().startActivity(intent);
    }
}
