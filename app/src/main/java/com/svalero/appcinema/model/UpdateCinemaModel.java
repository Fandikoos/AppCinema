package com.svalero.appcinema.model;

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
        Call<Void> callUpdate = api.updateCinema(cinemaId, updateCinema);
        callUpdate.enqueue(callback);
    }
}
