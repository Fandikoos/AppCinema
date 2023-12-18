package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Cinema;

public interface CinemaModifyContract {

    interface Model {
        interface OnModifyCinemaListener {
            void onModifyCinemaSuccess(Cinema cinema);
            void onModifyCinemaError(String message);
        }

        void modifyCinema(Cinema modifyCinema, OnModifyCinemaListener listener);
    }

    interface View {
        void showCinema(Cinema cinema);
    }

    interface Presenter {
        void modifyCinema(Cinema modifyCinema);
    }
}
