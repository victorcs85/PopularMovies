package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import io.reactivex.Maybe;
/**
 * Created by victorcs on 12/15/2018.
 */
public interface IMoviesRepository {
    Maybe< ArrayList<Movie> > getPopularMoviesList(int index);
    Maybe< ArrayList<Movie> > getTopRatedMoviesList(int index);
}
