package com.svalero.appcinema.presenter;

import com.svalero.appcinema.R;
import com.svalero.appcinema.contract.UpdateMovieContract;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.model.UpdateMovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMoviePresenter implements UpdateMovieContract.Presenter {

    private UpdateMovieContract.View view;
    private UpdateMovieContract.Model model;

    public UpdateMoviePresenter(UpdateMovieContract.View view){
        this.view = view;
        model = new UpdateMovieModel();
    }

    @Override
    public void updateMovie(long movieId, Movie updateMovie) {
        model.updateMovie(movieId, updateMovie, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    view.showSuccessMessage((R.string.updateMovieAtributes));
                } else {
                    view.showErrorMessage((R.string.errorUpdateMovieAtributes));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.showErrorMessage(R.string.errorRed);
            }
        });
    }
}
