package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.R;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;

/**
 * Created by victorcs on 12/18/2018.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> moviesList;

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;
        public Movie movie;

        MovieViewHolder(View view) {
            super(view);
            ivMovie = view.findViewById(R.id.ivMovie);
        }
    }


    public MoviesAdapter(Context context, ArrayList<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_image, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.movie = movie;
        String movieImagePath = Constants.MOVIE_URL_IMAGE + Constants.MOVIE_SIZE_IMAGE + movie.getPosterPath();
        setImage(holder, movieImagePath);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    void addItems(ArrayList<Movie> newMovies) {
        moviesList.addAll(newMovies);
        notifyDataSetChanged();
    }

    private void setImage(@NonNull MovieViewHolder holder, String movieImagePath) {
        new Handler().post(() -> Glide.with(context).load(movieImagePath).into(holder.ivMovie));
    }
}