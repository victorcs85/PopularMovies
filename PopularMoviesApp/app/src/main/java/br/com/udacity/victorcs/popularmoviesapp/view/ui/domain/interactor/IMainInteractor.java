package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.interactor;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import io.reactivex.Maybe;

/**
 * Created by victorcs on 12/15/2018.
 */
public interface IMainInteractor {
    Maybe< ArrayList<Movie> > getPopularMoviesList(int index);
    Maybe< ArrayList<Movie> > getTopRatedMoviesList(int index);
}
