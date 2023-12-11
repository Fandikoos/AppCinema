package com.svalero.appcinema.model;

import android.util.Log;

import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.contract.RegisterCinemaContract;
import com.svalero.appcinema.domain.Cinema;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCinemaModel implements RegisterCinemaContract.Model {

    @Override
    public void registerCinema(OnRegisterCinemaListener listener, Cinema cinema) {

        CinemaApiInterface api = CinemaApi.buildInstance();
        Call<Cinema> addCinemaCall = api.addCinema(cinema);
        addCinemaCall.enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                listener.onRegisterCinemaSuccess();
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {
                Log.e("addCinema", t.getMessage());
                listener.onRegisterCinemaError("No se ha podido conectar con el servidor");
            }
        });

    }
}
