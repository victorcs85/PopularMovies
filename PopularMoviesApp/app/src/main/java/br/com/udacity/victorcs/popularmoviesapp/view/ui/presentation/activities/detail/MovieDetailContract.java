package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.detail;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.core.BaseView;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.core.MvpPresenter;

/**
 * Created by victorcs on 12/18/2018.
 */
public interface MovieDetailContract {

    interface View extends BaseView {
        void setupComponents(String movieTitle, String movieImage,
                             String movieYear, String movieRating, String movieSynopsis);
    }

    abstract class Presenter extends MvpPresenter<MovieDetailContract.View> {

        public Presenter(View view) {
            super(view);
        }

        abstract void callShowMessage(String errorMessage);
        abstract void setSelectedMovie(Movie selectedMovie);
    }
}
