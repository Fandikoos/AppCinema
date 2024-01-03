package com.svalero.appcinema.adapter;

import android.app.AlertDialog;
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
import com.svalero.appcinema.domain.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvDirector.setText(movie.getDirector());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDirector;
        public Button deleteButton;
        public Button detailsButton;
        public Button doButton;
        public View parentView;


        public MovieHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvTitle = view.findViewById(R.id.movie_title);
            tvDirector = view.findViewById(R.id.movie_director);
            deleteButton = view.findViewById(R.id.movie_delete);
            doButton = view.findViewById(R.id.add_movie_button);
            detailsButton = view.findViewById(R.id.movie_details);

            //Borrar una película
            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Movie movie = movies.get(position);

                //Borramos la película en local
                movies.remove(position);
                notifyItemRemoved(position);

                //La borramos en la api
                deleteMovieFromApi(movie.getId());
            });
        }

        private void deleteMovieFromApi(long movieId){
            CinemaApiInterface api = CinemaApi.buildInstance();
            Call<Void> deleteCall = api.deleteMovie(movieId);
            deleteCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        showSuccessDialog((R.string.succedDeleteMovie));
                    } else {
                        showFailureDialog((R.string.errorDeleteMovie));
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    showFailureDialog(R.string.errorRedCinema);
                }
            });
        }

        //Metodo para que se de una respuesta al usuario cuando la eliminacion de la película haya sido la correcta
        private void showSuccessDialog(int message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());
            builder.setTitle(R.string.succed)
                    .setMessage(message)
                    .setPositiveButton(R.string.acept, (dialog, which) -> {})
                    .show();
        }

        //Metodo para que se de una respuesta al usuario cuando la eliminacion de la película haya sido la correcta
        private void showFailureDialog(int message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());
            builder.setTitle(R.string.error)
                    .setMessage(message)
                    .setPositiveButton(R.string.acept, (dialog, which) -> {})
                    .show();
        }
    }
}
