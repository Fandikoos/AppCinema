package com.svalero.appcinema.api;

import com.svalero.appcinema.domain.Cinema;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CinemaApiInterface {

    @GET("cinemas")
    Call<List<Cinema>> getCinemas();

    @POST("cinemas")
    Call<Cinema> addCinema(@Body Cinema cinema);

    @DELETE("cinema/{cinemaId}")
    Call<Void> deleteCinema(@Path("cinemaId") long cinemaId);
}
