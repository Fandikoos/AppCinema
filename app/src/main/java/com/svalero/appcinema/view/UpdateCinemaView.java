package com.svalero.appcinema.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appcinema.R;


import com.svalero.appcinema.contract.UpdateCinemaContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.presenter.UpdateCinemaPresenter;



public class UpdateCinemaView extends AppCompatActivity implements UpdateCinemaContract.View {

    private UpdateCinemaContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cinema);

        //Cargamos los datos necesarios para establecer los textviews con los valores del cinema
        Intent intent = getIntent();
        String cinemaName = intent.getStringExtra("cinemaName");
        int cinemaCapacity = intent.getIntExtra("cinemaCapacity", 0);
        float cinemaRating = intent.getFloatExtra("cinemaRating", 0);

        //Recogemos los textviews en funcion de la id
        TextView tvName = findViewById(R.id.getTextViewCinemaName);
        TextView tvCapacity = findViewById(R.id.getTextViewCinemaCapacity);
        TextView tvRating = findViewById(R.id.getTextViewCinemaRating);

        //Asignamos los valores a cada textview
        tvName.setText(getString(R.string.nameUpdate).concat(cinemaName));
        tvCapacity.setText(getString(R.string.capacityUpdate).concat(String.valueOf(cinemaCapacity)));
        tvRating.setText(getString(R.string.ratingUpdate).concat(String.valueOf(cinemaRating)));

        presenter = new UpdateCinemaPresenter(this);


    }

    public void updateCinema (View view){
        Log.i("Info", "hola");
        EditText etName = findViewById(R.id.editTextCinemaName);
        EditText etCapacity = findViewById(R.id.editTextCinemaCapacity);
        EditText etRating = findViewById(R.id.editTextCinemaRating);

        Intent intent = getIntent();
        long cinemaId = intent.getLongExtra("cinemaId", 0);
        double cinemaLatitude = intent.getDoubleExtra("latitude", 0.0);
        double cinemaLongitude = intent.getDoubleExtra("longitude", 0.0);

        String cinemaName = etName.getText().toString();
        int cinemaCapacity = Integer.parseInt(etCapacity.getText().toString());
        float cinemaRating = Float.parseFloat(etRating.getText().toString());

        Cinema updateCinema = new Cinema(cinemaName, cinemaCapacity, cinemaRating, cinemaLatitude, cinemaLongitude);
        presenter.updateCinema(cinemaId, updateCinema);
    }

    @Override
    public void showSuccessMessage(int message) {
        Toast.makeText(this, getResources().getString(message),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(int message) {
        Toast.makeText(this, getResources().getString(message),Toast.LENGTH_LONG).show();
    }
}
