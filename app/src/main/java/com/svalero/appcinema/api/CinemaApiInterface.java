package com.svalero.appcinema.api;

import com.svalero.appcinema.contract.MovieListContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.domain.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CinemaApiInterface {

    @GET("cinemas")
    Call<List<Cinema>> getCinemas();

    @GET("cinema/{cinemaName}")
    Call<Cinema> getCinemaDetails(@Query("cinemaName") String cinemaName);

    @POST("cinemas")
    Call<Cinema> addCinema(@Body Cinema cinema);

    @PUT("cinema/{cinemaId}")
    Call<Cinema> modifyCinema(@Path("cinemaName") String cinemaName,@Body Cinema cinema);

    @DELETE("cinema/{cinemaId}")
    Call<Void> deleteCinema(@Path("cinemaId") long cinemaId);

    @GET("movies")
    Call<List<Movie>> getMovies();

    @POST("movies")
    Call<Movie> addMovie(@Body Movie movie);
}
