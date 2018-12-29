package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.detail;

import javax.inject.Inject;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;

/**
 * Created by victorcs on 12/18/2018.
 */
public class MovieDetailPresenter extends MovieDetailContract.Presenter {

    public static final String MESSAGE = "Não foi possível carregar os dados do filme.";
    private MovieDetailContract.View view;

    public MovieDetailPresenter() {
    }

    public void setView(MovieDetailContract.View view) {
        this.view = view;
        super.onAttach(view);
    }

    @Override
    void onCreate() {
        view.showProgress();
    }

    @Override
    void callShowMessage(String errorMessage) {
        view.showMessage(errorMessage);
    }

    @Override
    void setSelectedMovie(Movie selectedMovie) {

        view.hideProgress();
        try {

            if (selectedMovie != null) {

                String movieTitle = selectedMovie.getTitle();
                String movieImage = Constants.MOVIE_URL_IMAGE + Constants.MOVIE_SIZE_IMAGE +
                        selectedMovie.getPosterPath();
                String movieYear = selectedMovie.getReleaseDate();
                movieYear = movieYear.split("-")[0];
                String movieRating = selectedMovie.getVoteAverage().toString();
                String movieSynopsis = selectedMovie.getOverview();
                view.setupComponents(movieTitle, movieImage, movieYear, movieRating, movieSynopsis);

            } else {

                view.showMessage(MESSAGE);

            }
        } catch (Exception ex) {

            view.showMessage(MESSAGE);
            TimberHelper.e(Constants.ERROR, ex.toString());

        }

    }

}
