package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Cinema;

import java.util.List;

public interface CinemaListContract {

    interface Model {
        interface OnLoadCinemasListener {
            void OnLoadCinemaSuccess(List<Cinema> cinemas);
            void OnLoadCinemasError(String message);
        }

        interface View {
            void listCinemas(List<Cinema> cinemas);
            void showMessage(String message);
        }

        interface Presenter {
            void loadAllCinemas();
        }
    }
}
