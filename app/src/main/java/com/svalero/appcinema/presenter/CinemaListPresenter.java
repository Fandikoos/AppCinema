package com.svalero.appcinema.presenter;

import com.svalero.appcinema.contract.CinemaListContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.model.CinemaListModel;

import java.util.List;

public class CinemaListPresenter implements CinemaListContract.Presenter, CinemaListContract.Model.OnLoadCinemasListener {

    private CinemaListContract.View view;
    private CinemaListContract.Model model;

    public CinemaListPresenter(CinemaListContract.View view){
        this.view = view;
        model = new CinemaListModel();
    }

    @Override
    public void loadAllCinemas(){
        model.loadAllCinemas(this);
    }

    @Override
    public void onLoadCinemaSuccess(List<Cinema> cinemas){
        view.listCinemas(cinemas);
    }

    @Override
    public void onLoadCinemasError(String message){
        view.showMessage(message);
    }

}
