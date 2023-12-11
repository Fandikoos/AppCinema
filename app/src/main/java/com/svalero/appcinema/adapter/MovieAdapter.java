package com.svalero.appcinema.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.appcinema.R;
import com.svalero.appcinema.domain.Movie;

import java.util.List;

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
            doButton = view.findViewById(R.id.add_movie_button);
            detailsButton = view.findViewById(R.id.movie_details);

        }
    }
}
