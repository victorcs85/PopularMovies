package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.detail;

import javax.inject.Inject;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;

/**
 * Created by victorcs on 12/18/2018.
 */
public class MovieDetailPresenter extends MovieDetailContract.Presenter {

    private MovieDetailContract.View view;

    @Inject
    public MovieDetailPresenter(MovieDetailContract.View view) {
        super(view);
        this.view = view;
    }

    @Override
    void callShowMessage(String errorMessage) {
        view.showMessage(errorMessage);
    }

    @Override
    void setSelectedMovie(Movie selectedMovie) {

        if (selectedMovie != null) {

            String movieTitle = selectedMovie.getTitle();
            String movieImage = Constants.MOVIE_URL_IMAGE + Constants.MOVIE_SIZE_IMAGE +
                    selectedMovie.getPosterPath();
            String movieYear = selectedMovie.getReleaseDate();
            String movieRating = selectedMovie.getVoteAverage().toString();
            String movieSynopsis = selectedMovie.getOverview();
            view.setupComponents(movieTitle, movieImage, movieYear, movieRating, movieSynopsis);

        } else {

            view.showMessage("Não foi possível carregar os dados do filme.");

        }

    }

}
