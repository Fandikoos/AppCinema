package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Movie;

public interface RegisterMovieContract {

    interface Model {
        interface OnRegisterMoviesListener {
            void onRegisterMovieSuccess();
            void onRegisterMoviesError(String message);
        }
        void registerMovie(OnRegisterMoviesListener listener, Movie movie);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void registerMovie(Movie movie);
    }
}
