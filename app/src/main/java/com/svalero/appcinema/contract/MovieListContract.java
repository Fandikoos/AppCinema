package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Movie;

import java.util.List;

public interface MovieListContract {

    interface Model {
        interface OnLoadMovieListener {
            void onLoadMovieSuccess(List<Movie> movies);
            void onLoadMoviesError(String message);
        }
        void loadAllMovies(OnLoadMovieListener listener);
    }

    interface View {
        void listMovies(List<Movie> movies);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllMovies();
    }
}
