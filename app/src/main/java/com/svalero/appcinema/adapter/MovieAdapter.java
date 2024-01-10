package com.svalero.appcinema.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appcinema.R;
import com.svalero.appcinema.api.CinemaApi;
import com.svalero.appcinema.api.CinemaApiInterface;
import com.svalero.appcinema.db.AppDatabase;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.view.UpdateMovieView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private AppDatabase db;
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies, AppDatabase db){
        this.movies = movies;
        this.db = db;
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

        //Vemos si la peli que sacamos de la api está en la bbdd de Room de favoritos con el método
        holder.validateFavs(position);

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
        public Button modifyButton;
        public Button doButton;
        public View parentView;
        public Button favButton;


        public MovieHolder(@NonNull View view) {
            super(view);
            parentView = view;

            tvTitle = view.findViewById(R.id.movie_title);
            tvDirector = view.findViewById(R.id.movie_director);
            deleteButton = view.findViewById(R.id.movie_delete);
            doButton = view.findViewById(R.id.add_movie_button);
            modifyButton = view.findViewById(R.id.movie_details);
            favButton = view.findViewById(R.id.movie_fav);

            modifyButton.setOnClickListener(v -> goToUpdateMovie(view));

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

            //Añadir una película a favoritos
            favButton.setOnClickListener(v -> {
                //Cogemos la posición de la película en cuestión
                int position = getAdapterPosition();
                Movie movie = movies.get(position);

                //Cogemos el texto actual del botón
                String actualText = String.valueOf(favButton.getText());
                Log.i("Texto actual", actualText);

                //Si al pulsar el botón, el texto es igual eliminar de favoritos, cogemos esa movie por id y la borramos
                //de nuestra base de datos de Room y cambiamos el texto a favoritos
                if (actualText.equals("Eliminar de favoritos")){
                    Log.i("id movie", String.valueOf(movie.getId()));
                    Movie movieFav = db.movieDao().findMovieById(movie.getId());
                    Log.i("movie fav", String.valueOf(movieFav));
                    Log.i("id movie fav", String.valueOf(movieFav.getId()));
                    db.movieDao().deleteMovie(movieFav);
                    favButton.setText(R.string.favoriteAdd);
                    showSuccessDialog((R.string.removeMovieFromFavorites));
                } else {
                    //Sino entonces la insertamos en nuestra base de datos de Room y cambiamos el texto del botón
                    db.movieDao().insertMovie(movie);
                    favButton.setText(R.string.removeFromFavorite);
                    showSuccessDialog((R.string.addMovieToFavorites));
                }

            });
        }

        //Método para comprobar cuales de las pelis están ya en la bbdd de Room de pelis favoritas para que ya aparezcan con el botón cambiado
        private void validateFavs(int position){
            Movie movie = movies.get(position);
            Movie movieFav = db.movieDao().findMovieById(movie.getId());
            Log.i("pelicula fav", String.valueOf(movieFav));
            if (movieFav != null){
                favButton.setText(R.string.removeFromFavorite);
            }


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
                    showFailureDialog(R.string.errorRed);
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

        //Método para modificar una película
        private void goToUpdateMovie(View itemView){
            Intent intent = new Intent(itemView.getContext(), UpdateMovieView.class);
            Movie movie = movies.get(getAdapterPosition());
            intent.putExtra("movieId", movie.getId());
            intent.putExtra("movieTitle", movie.getTitle());
            intent.putExtra("movieDirector", movie.getDirector());
            intent.putExtra("movieGenre", movie.getGenre());
            intent.putExtra("movieDuration", movie.getDuration());
            itemView.getContext().startActivity(intent);
        }
    }
}
