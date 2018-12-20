package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import io.reactivex.Maybe;
/**
 * Created by victorcs on 12/13/2018.
 */
public class MoviesRepository implements IMoviesRepository {

    private IMoviesRepository iMoviesRepository;

    @Inject public MoviesRepository(IMoviesRepository iMoviesRepository) {
        this.iMoviesRepository = iMoviesRepository;
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
