package com.svalero.appcinema.model;

import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.contract.UpdateMovieContract;
import com.svalero.appcinema.domain.Movie;

import retrofit2.Call;
import retrofit2.Callback;

public class UpdateMovieModel implements UpdateMovieContract.Model {

    @Override
    public void updateMovie(long movieId, Movie updateMovie, Callback<Void> callback) {
        CinemaApiInterface api = CinemaApi.buildInstance();
        Call<Void> callUpdate = api.updateMovie(updateMovie, movieId);
        callUpdate.enqueue(callback);
    }
}
