package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.retrofit.MoviesRetrofit;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import io.reactivex.Maybe;
/**
 * Created by victorcs on 12/13/2018.
 */
public class MoviesRepository implements IMoviesRepository {

    private IMoviesRepository iMoviesRepository;

    public MoviesRepository() {
        this.iMoviesRepository = new MoviesRetrofit();
    }

    @Override
    public Maybe<ArrayList<Movie>> getPopularMoviesList(int index) {
        return iMoviesRepository.getPopularMoviesList(index);
    }

    @Override
    public Maybe<ArrayList<Movie>> getTopRatedMoviesList(int index) {
        return iMoviesRepository.getTopRatedMoviesList(index);
    }
}
