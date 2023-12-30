package com.svalero.appcinema.presenter;

import android.util.Log;

import com.svalero.appcinema.R;
import com.svalero.appcinema.contract.UpdateCinemaContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.model.UpdateCinemaModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCinemaPresenter implements UpdateCinemaContract.Presenter {

   private UpdateCinemaContract.View view;
   private UpdateCinemaContract.Model model;

   public UpdateCinemaPresenter(UpdateCinemaContract.View view){
       this.view = view;
       model = new UpdateCinemaModel();
   }

   @Override
    public void updateCinema(long cinemaId, Cinema updateCinema) {
        model.updateCinema(cinemaId, updateCinema, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Correcto", "Correcto");
                if (response.isSuccessful()) {
                    view.showSuccessMessage((R.string.updateCinemaAtributes));
                } else {
                    view.showErrorMessage((R.string.errorUpdateCinemaAtributes));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Mal", "MAL");
                view.showErrorMessage((R.string.errorRedCinema));

            }
        });
    }
}
