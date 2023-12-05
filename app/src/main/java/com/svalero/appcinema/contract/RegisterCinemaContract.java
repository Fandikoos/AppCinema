package com.svalero.appcinema.contract;

import com.svalero.appcinema.domain.Cinema;

public interface RegisterCinemaContract {

    interface Model {
        interface OnRegisterCinemaListener {
            void onRegisterCinemaSuccess();
            void onRegisterCinemaError(String message);
        }
        void registerCinema(OnRegisterCinemaListener listener, Cinema cinema);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void registerCinema(Cinema cinema);
    }
}
