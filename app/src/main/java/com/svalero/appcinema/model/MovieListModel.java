package com.svalero.appcinema.model;

import android.util.Log;

import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.contract.MovieListContract;
import com.svalero.appcinema.domain.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    @Override
    public void loadAllMovies(OnLoadMovieListener listener) {
        CinemaApiInterface api = CinemaApi.buildInstance();
        Call<List<Movie>> getMoviesCall = api.getMovies();
        getMoviesCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                Log.e("getMovies", response.message());
                List<Movie> movies = response.body();
                listener.onLoadMovieSuccess(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("getMovies", t.getMessage());
                listener.onLoadMoviesError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
