package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.interactor;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository.IMoviesRepository;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository.MoviesRepository;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import io.reactivex.Maybe;

/**
 * Created by victorcs on 12/15/2018.
 */
public class MainInteractor implements IMainInteractor {

    private IMoviesRepository iMoviesRepository;

    public MainInteractor() {
        this.iMoviesRepository = new MoviesRepository();
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
