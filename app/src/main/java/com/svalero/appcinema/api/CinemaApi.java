package com.svalero.appcinema.api;

import static com.svalero.appcinema.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CinemaApi {

    public static CinemaApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(CinemaApiInterface.class);
    }
}
