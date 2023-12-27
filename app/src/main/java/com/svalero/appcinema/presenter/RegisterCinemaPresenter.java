package com.svalero.appcinema.presenter;

import com.svalero.appcinema.contract.RegisterCinemaContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.model.RegisterCinemaModel;

public class RegisterCinemaPresenter implements RegisterCinemaContract.Presenter, RegisterCinemaContract.Model.OnRegisterCinemaListener {

    private RegisterCinemaContract.Model model;
    private RegisterCinemaContract.View view;

    public RegisterCinemaPresenter(RegisterCinemaContract.View view){
        this.view = view;
        model = new RegisterCinemaModel();
    }

    @Override
    public void registerCinema(Cinema cinema) {
        model.registerCinema(this, cinema);
    }
    @Override
    public void onRegisterCinemaSuccess() {
        view.showMessage("El cine se ha registrado correctamente");

    }

    @Override
    public void onRegisterCinemaError(String message) {
        view.showMessage(message);
    }


}
