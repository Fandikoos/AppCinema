package com.svalero.appcinema.presenter;

import com.svalero.appcinema.contract.RegisterMovieContract;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.model.RegisterMovieModel;

public class RegisterMoviePresenter implements RegisterMovieContract.Presenter, RegisterMovieContract.Model.OnRegisterMoviesListener {

    private RegisterMovieContract.Model model;
    private RegisterMovieContract.View view;

    public RegisterMoviePresenter(RegisterMovieContract.View view) {
        this.view = view;
        model = new RegisterMovieModel();
    }
    @Override
    public void onRegisterMovieSuccess() {
        view.showMessage("La película se ha registrado correctamente");
    }

    @Override
    public void onRegisterMoviesError(String message) {
        view.showMessage(message);
    }

    @Override
    public void registerMovie(Movie movie) {
        model.registerMovie(this, movie);
    }
}
