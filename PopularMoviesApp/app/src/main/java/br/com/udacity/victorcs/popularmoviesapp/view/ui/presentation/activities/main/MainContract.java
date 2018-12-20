package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.core.BaseView;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.core.MvpPresenter;
import io.reactivex.Maybe;
/**
 * Created by victorcs on 12/13/2018.
 */
public interface MainContract {

    interface View extends BaseView {
        void initComponent();
        void setMoviesListIntoRecyclerView(ArrayList<Movie> movies);
    }

    abstract class Presenter extends MvpPresenter<MainContract.View> {

        public Presenter(View view) {
            super(view);
        }

        abstract void onCreate();
        abstract void getPopularMovies(int index);
        abstract void getTopRatedMovies(int index);
    }
}
