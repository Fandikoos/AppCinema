package com.svalero.appcinema.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appcinema.R;
import com.svalero.appcinema.db.AppDatabase;
import com.svalero.appcinema.domain.Movie;

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
    }

    @Override
    public int getItemCount(){
        return movies.size();
    }

    public class FavoriteMovieHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDirector;
        public View parentView;

        public FavoriteMovieHolder(@NonNull View view){
            super(view);
            parentView = view;

            tvTitle = view.findViewById(R.id.movie_fav_title);
            tvDirector = view.findViewById(R.id.movie_fav_director);
        }
    }
}
