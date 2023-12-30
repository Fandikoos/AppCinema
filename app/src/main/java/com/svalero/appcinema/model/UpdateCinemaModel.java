package com.svalero.appcinema.model;

import android.util.Log;

import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.contract.UpdateCinemaContract;
import com.svalero.appcinema.domain.Cinema;

import retrofit2.Call;
import retrofit2.Callback;

public class UpdateCinemaModel implements UpdateCinemaContract.Model {

    @Override
    public void updateCinema(long cinemaId, Cinema updateCinema, Callback<Void> callback) {
        CinemaApiInterface api = CinemaApi.buildInstance();
        Log.i("id cinema", updateCinema.getName());
        Log.i("id cinema", String.valueOf(updateCinema.getCapacity()));
        Log.i("id cinema", String.valueOf(updateCinema.getRating()));
        Log.i("id cinema", String.valueOf(cinemaId));
        Call<Void> callUpdate = api.updateCinema(updateCinema, cinemaId);
        callUpdate.enqueue(callback);
    }
}
