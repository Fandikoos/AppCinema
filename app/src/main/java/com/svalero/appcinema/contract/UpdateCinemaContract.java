package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Cinema;

import retrofit2.Callback;

public interface UpdateCinemaContract {

    interface Model {
        void updateCinema(long cinemaId, Cinema updateCinema, Callback<Void> callback);
    }

    interface View {
        void showSuccessMessage(int message);
        void showErrorMessage(int message);
    }

    interface Presenter {
        void updateCinema(long cinemaId, Cinema updateCinema);
    }
}
