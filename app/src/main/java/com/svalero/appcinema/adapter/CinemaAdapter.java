package com.svalero.appcinema.adapter;

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

    public class CinemaHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvRating;
        public Button deleteButton;
        public Button detailsButton;
        public Button doButton;
        public View parentView;

        public CinemaHolder(@NonNull View view){
            super(view);
            parentView = view;

            tvName = view.findViewById(R.id.cinema_name);
            tvRating = view.findViewById(R.id.cinema_rating);
            detailsButton = view.findViewById(R.id.cinema_details)
            deleteButton = view.findViewById(R.id.cinema_delete);
            doButton = view.findViewById(R.id.add_cinema_button);

            //Para cuando pulse el boton de mas para hacer un cine, details de cine o delete de cine
            //doButton.setOnClickListener(v -> doCinema(cinema));
            //detailsButton.setOnClickListener(v -> goToCinemaDetails(cinema));
            //deleteButton.setOnClickListener(v -> deleteCinema(cinema));

            //Metodo de crear un cine

            //Metodo de ir a detalles del cine
        }
    }
}
