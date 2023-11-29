package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Cinema;

import java.util.List;

public interface CinemaListContract {

    interface Model {
        interface OnLoadCinemasListener {
            void onLoadCinemaSuccess(List<Cinema> cinemas);

            void onLoadCinemasError(String message);
        }

        void loadAllCinemas(OnLoadCinemasListener listener);

    }

    interface View {
        void listCinemas(List<Cinema> cinemas);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllCinemas();
    }

}
