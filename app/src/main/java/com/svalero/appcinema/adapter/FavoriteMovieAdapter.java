package com.svalero.appcinema.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appcinema.R;
import com.svalero.appcinema.domain.Movie;
import com.svalero.appcinema.view.UpdateFavoriteMoviesView;


import java.util.List;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieHolder> {

    private List<Movie> movies;

    public FavoriteMovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public FavoriteMovieHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_favorite_list_item, parent, false);
        return new FavoriteMovieHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieAdapter.FavoriteMovieHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.tvTitle.setText(holder.itemView.getContext().getString(R.string.titleList) + movie.getTitle());
        holder.tvDirector.setText(holder.itemView.getContext().getString(R.string.directorList) + movie.getDirector());
        holder.tvDescription.setText(movie.getDescription());
    }

    @Override
    public int getItemCount(){
        return movies.size();
    }

    public class FavoriteMovieHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDirector;
        public TextView tvDescription;
        public Button btUpdateDescription;
        public View parentView;

        public FavoriteMovieHolder(@NonNull View view){
            super(view);
            parentView = view;

            tvTitle = view.findViewById(R.id.movie_fav_title);
            tvDirector = view.findViewById(R.id.movie_fav_director);
            tvDescription = view.findViewById(R.id.movie_fav_description);
            btUpdateDescription = view.findViewById(R.id.movie_fav_update);

            btUpdateDescription.setOnClickListener(v -> goToUpdateFavoriteMovie(view));
        }

        private void goToUpdateFavoriteMovie(View itemView) {
            Intent intent = new Intent(itemView.getContext(), UpdateFavoriteMoviesView.class);
            Movie movie = movies.get(getAdapterPosition());
            intent.putExtra("movieId", movie.getId());
            intent.putExtra("movieDescription", movie.getDescription());
            intent.putExtra("movieTitle", movie.getTitle());
            intent.putExtra("movieDirector", movie.getDirector());
            itemView.getContext().startActivity(intent);
        }
    }
}
