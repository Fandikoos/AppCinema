package com.svalero.appcinema.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.appcinema.R;
import com.svalero.appcinema.contract.RegisterMovieContract;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.presenter.RegisterMoviePresenter;

import java.time.LocalDate;

public class RegisterMovieView extends AppCompatActivity implements /*Style.OnStyleLoaded,
        OnMapClickListener,*/ RegisterMovieContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;
    private RegisterMovieContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        /*
        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);*/

        presenter = new RegisterMoviePresenter(this);
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
    /*
    @Override
    public boolean onMapClick(@NonNull Point point) {
        return false;
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }*/

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
}
