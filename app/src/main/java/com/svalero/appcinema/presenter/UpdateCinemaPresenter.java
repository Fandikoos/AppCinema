package com.svalero.appcinema.presenter;

import com.svalero.appcinema.contract.UpdateCinemaContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.model.RegisterMovieModel;
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
                if (response.isSuccessful()) {
                    view.showSuccessMessage("Datos del cine actualizados correctamente");
                } else {
                    view.showErrorMessage("Error al actualizar los datos del cine. Por favor, inténtalo de nuevo.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.showErrorMessage("Error de red. Por favor, verifica tu conexión e inténtalo de nuevo.");

            }
        });
    }
}
