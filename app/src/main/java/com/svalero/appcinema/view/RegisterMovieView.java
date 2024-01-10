package com.svalero.appcinema.view;

import static com.svalero.appcinema.util.Constants.DATABASE_NAME;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.appcinema.R;
import com.svalero.appcinema.contract.RegisterMovieContract;
import com.svalero.appcinema.db.AppDatabase;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.presenter.RegisterMoviePresenter;

import java.time.LocalDate;

public class RegisterMovieView extends AppCompatActivity implements RegisterMovieContract.View {

    private AppDatabase db;
    private RegisterMovieContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        presenter = new RegisterMoviePresenter(this);

        ImageView imageView = findViewById(R.id.movieCartel);
        imageView.setImageResource(R.drawable.cartelera);
    }

    public void createMovie(View view){
        EditText etTitle = findViewById(R.id.title_movie);
        EditText etDirector = findViewById(R.id.director_movie);
        EditText etGenre = findViewById(R.id.genre_movie);
        EditText etDuration = findViewById(R.id.duration_movie);

        //EditText etRelaseDate = findViewById(R.id.releaseDate_movie);

        String movieTitle = etTitle.getText().toString();
        String directorMovie = etDirector.getText().toString();
        String genreMovie = etGenre.getText().toString();
        int durationMovie = Integer.parseInt(etDuration.getText().toString());
        //LocalDate releaseDateMovie = LocalDate.parse(etRelaseDate.getText().toString());

        Movie movie = new Movie(movieTitle, directorMovie, genreMovie, durationMovie/*, releaseDateMovie*/);
        presenter.registerMovie(movie);
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
}
