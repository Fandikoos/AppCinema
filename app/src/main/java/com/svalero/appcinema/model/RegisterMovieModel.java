package com.svalero.appcinema.model;

import android.util.Log;

import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.contract.RegisterMovieContract;
import com.svalero.appcinema.domain.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMovieModel implements RegisterMovieContract.Model {

    @Override
    public void registerTask(OnRegisterMoviesListener listener, Movie movie) {
        CinemaApiInterface api = CinemaApi.buildInstance();
        Call<Movie> addMovieCall = api.addMovie(movie);
        addMovieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                listener.onRegisterMovieSuccess();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("AddMovie", t.getMessage());
                listener.onRegisterMoviesError("Se ha producido un error en el servidor");
            }
        });
    }
}
