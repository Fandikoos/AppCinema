package com.svalero.appcinema.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appcinema.R;
import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.view.UpdateCinemaView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.CinemaHolder> {

    private List<Cinema> cinemas;
    public CinemaAdapter(List<Cinema> cinemas){
        this.cinemas = cinemas;
    }

    @NonNull
    @Override
    public CinemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cinema_list_item, parent, false);
        return new CinemaHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CinemaHolder holder, int position) {
        Cinema cinema = cinemas.get(position);
        Log.d("Prueba", cinema.getName());

        holder.tvName.setText(cinema.getName());
        holder.tvRating.setText(String.valueOf(cinema.getRating()));

    }



    @Override
    public int getItemCount() {
        return cinemas.size();
    }

    public class CinemaHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvRating;
        public Button deleteButton;
        public Button modifyButton;
        public Button doButton;
        public Button movieButton;
        public View parentView;

        public CinemaHolder(@NonNull View view){
            super(view);
            parentView = view;

            tvName = view.findViewById(R.id.cinema_name);
            tvRating = view.findViewById(R.id.cinema_rating);
            modifyButton = view.findViewById(R.id.cinema_modify);
            deleteButton = view.findViewById(R.id.cinema_delete);
            doButton = view.findViewById(R.id.add_cinema_button);
            movieButton = view.findViewById(R.id.go_to_movies);


            modifyButton.setOnClickListener(v -> goToUpdateCinema(view));

            //Borrar un cine
            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Cinema cinema = cinemas.get(position);

                //Borramos el cine en local
                cinemas.remove(position);
                notifyItemRemoved(position);

                //Llamamos a la api para eliminar el cine
                deleteCinemaFromApi(cinema.getId());

            });

        }

        //Metodo para borrar un cine en función de la id
        private void deleteCinemaFromApi(long cinemaId){
            CinemaApiInterface api = CinemaApi.buildInstance();
            Call<Void> deleteCall = api.deleteCinema(cinemaId);
            deleteCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        showSuccessDialog((R.string.succedDeleteCinema));
                    } else {
                        showFailureDialog((R.string.errorDeleteCinema));
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    showFailureDialog((R.string.errorRed));
                }
            });

        }

        //Metodo para que se de una respuesta al usuario cuando la eliminacion del cine haya sido la correcta
        private void showSuccessDialog(int message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());
            builder.setTitle(R.string.succed)
                    .setMessage(message)
                    .setPositiveButton(R.string.acept, (dialog, which) -> {})
                    .show();
        }

        //Metodo para que se de una respuesta al usuario cuando la eliminacion del cine haya sido la correcta
        private void showFailureDialog(int message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());
            builder.setTitle(R.string.error)
                    .setMessage(message)
                    .setPositiveButton(R.string.acept, (dialog, which) -> {})
                    .show();
        }

        //Metodo de ir a modifica un cine
        private void goToUpdateCinema(View itemView){
            Intent intent = new Intent(itemView.getContext(), UpdateCinemaView.class);
            Cinema cinema = cinemas.get(getAdapterPosition());
            intent.putExtra("cinemaId", cinema.getId());
            intent.putExtra("cinemaName", cinema.getName());
            intent.putExtra("cinemaCapacity", cinema.getCapacity());
            intent.putExtra("cinemaRating", cinema.getRating());
            intent.putExtra("latitude", cinema.getLatitude());
            intent.putExtra("longitude", cinema.getLongitude());
            Log.i("id", String.valueOf(cinema.getId()));
            itemView.getContext().startActivity(intent);
        }
    }
}
