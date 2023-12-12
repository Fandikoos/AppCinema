package com.svalero.appcinema.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appcinema.R;
import com.svalero.appcinema.domain.Cinema;

import java.util.List;

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
        public Button detailsButton;
        public Button doButton;
        public Button movieButton;
        public View parentView;

        public CinemaHolder(@NonNull View view){
            super(view);
            parentView = view;

            tvName = view.findViewById(R.id.cinema_name);
            tvRating = view.findViewById(R.id.cinema_rating);
            detailsButton = view.findViewById(R.id.cinema_details);
            deleteButton = view.findViewById(R.id.cinema_delete);
            doButton = view.findViewById(R.id.add_cinema_button);
            movieButton = view.findViewById(R.id.go_to_movies);


            //Para cuando pulse el boton de mas para hacer un cine, details de cine o delete de cine
            //doButton.setOnClickListener(v -> doCinema(cinema));
            //detailsButton.setOnClickListener(v -> goToCinemaDetails(cinema));
            //deleteButton.setOnClickListener(v -> deleteCinema(cinema));

        }

        //Metodo de crear un cine


        //Metodo de ir a detalles del cine
    }
}
