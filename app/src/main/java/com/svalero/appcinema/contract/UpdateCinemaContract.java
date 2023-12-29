package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Cinema;

import retrofit2.Callback;

public interface UpdateCinemaContract {

    interface Model {
        void updateCinema(long cinemaId, Cinema updateCinema, Callback<Void> callback);
    }

    interface View {
        void showSuccessMessage(String message);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void updateCinema(long cinemaId, Cinema updateCinema);
    }
}
