package com.svalero.appcinema.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.Plugin;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.appcinema.R;
import com.svalero.appcinema.contract.RegisterCinemaContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.presenter.RegisterCinemaPresenter;

public class RegisterCinemaView extends AppCompatActivity implements Style.OnStyleLoaded,
        /*OnMapClickListener,*/ RegisterCinemaContract.View {



    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;
    private RegisterCinemaContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cinema);

        /*mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);*/

        presenter = new RegisterCinemaPresenter(this);
    }

    public void createCinema(View view){
        EditText etName = findViewById(R.id.name_cinema);
        EditText etCapacity = findViewById(R.id.capacity_cinema);
        EditText etRating = findViewById(R.id.rating_cinema);

        String cinemaName = etName.getText().toString();
        int cinemaCapacity = Integer.parseInt(etCapacity.getText().toString());
        float cinemaRating = Float.parseFloat(etRating.getText().toString());

        Cinema cinema = new Cinema(cinemaName, cinemaCapacity, cinemaRating);
        presenter.registerCinema(cinema);
    }
   /* @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude(), getString(R.string.here));
        return false;
    }*/

    /*private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }*/

    /*private void addMarker(double latitude, double longitude, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withTextField(title);
        pointAnnotationManager.create(pointAnnotationOptions);
    }*/

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
