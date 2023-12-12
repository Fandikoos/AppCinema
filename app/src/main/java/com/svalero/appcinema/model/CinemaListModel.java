package com.svalero.appcinema.model;

import android.util.Log;

import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.contract.CinemaListContract;
import com.svalero.appcinema.domain.Cinema;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CinemaListModel implements CinemaListContract.Model {


    @Override
    public void loadAllCinemas(OnLoadCinemasListener listener){
        CinemaApiInterface api = CinemaApi.buildInstance();
        Call<List<Cinema>> getCinemasCall = api.getCinemas();
        getCinemasCall.enqueue(new Callback<List<Cinema>>() {
            @Override
            public void onResponse(Call<List<Cinema>> call, Response<List<Cinema>> response) {
                Log.e("getCinemas", response.message());
                List<Cinema> cinemas = response.body();
                //Log.d("getCinemas", String.valueOf(cinemas.size()));
                listener.onLoadCinemaSuccess(cinemas);
            }

            @Override
            public void onFailure(Call<List<Cinema>> call, Throwable t) {
                Log.e("getCinemas", t.getMessage());
                listener.onLoadCinemasError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
