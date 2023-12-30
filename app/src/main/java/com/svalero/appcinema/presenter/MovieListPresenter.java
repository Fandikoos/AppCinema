package com.svalero.appcinema.presenter;

import com.svalero.appcinema.contract.MovieListContract;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.model.MovieListModel;

import java.util.List;

public class MovieListPresenter implements MovieListContract.Presenter, MovieListContract.Model.OnLoadMovieListener {

    private MovieListContract.View view;
    private MovieListContract.Model model;

    public MovieListPresenter(MovieListContract.View view){
        this.view = view;
        model = new MovieListModel();
    }
    @Override
    public void onLoadMovieSuccess(List<Movie> movies) {
        view.listMovies(movies);
    }

    @Override
    public void onLoadMoviesError(String message) {
        view.showMessage(message);
    }

    @Override
    public void loadAllMovies() {
        model.loadAllMovies(this);
    }
}
