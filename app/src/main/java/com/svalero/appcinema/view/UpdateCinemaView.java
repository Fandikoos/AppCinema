package com.svalero.appcinema.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.appcinema.R;
import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.contract.UpdateCinemaContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.model.UpdateCinemaModel;
import com.svalero.appcinema.presenter.UpdateCinemaPresenter;

import retrofit2.Call;

public class UpdateCinemaView extends AppCompatActivity implements UpdateCinemaContract.View {

    private UpdateCinemaContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cinema);

        presenter = new UpdateCinemaPresenter(this);
    }

    /*
    private void loadCinema(Cinema cinema){
        EditText etName = findViewById(R.id.editTextCinemaName);
        EditText etCapacity = findViewById(R.id.editTextCinemaCapacity);
        EditText etRating = findViewById(R.id.editTextCinemaRating);


        etName.setText(cinema.getName());
        etCapacity.setText(cinema.getCapacity());
        etRating.setText((int) cinema.getRating());
    }*/

    public void updateCinema (View view){
        EditText etName = findViewById(R.id.editTextCinemaName);
        EditText etCapacity = findViewById(R.id.editTextCinemaCapacity);
        EditText etRating = findViewById(R.id.editTextCinemaRating);

        /*
        etName.setText(cinema.getName());
        etCapacity.setText(cinema.getCapacity());
        etRating.setText((int) cinema.getRating());*/

        String cinemaName = etName.getText().toString();
        int cinemaCapacity = Integer.parseInt(etCapacity.getText().toString());
        float cinemaRating = Float.parseFloat(etRating.getText().toString());

        //Cinema updateCinema = new Cinema(cinemaName, cinemaCapacity, cinemaRating);
        //presenter.updateCinema(updateCinema.getId(), updateCinema);
    }
    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
}
