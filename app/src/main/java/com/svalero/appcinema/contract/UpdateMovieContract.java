package com.svalero.appcinema.contract;

import android.view.View;

import com.svalero.appcinema.domain.Movie;

import retrofit2.Callback;

public interface UpdateMovieContract {

    interface Model {
        void updateMovie(long movieId, Movie updateMovie, Callback<Void> callback);
    }

    interface View {
        void showSuccessMessage(int message);
        void showErrorMessage(int message);
    }

    interface Presenter {
        void updateMovie(long movieId, Movie updateMovie);
    }
}
