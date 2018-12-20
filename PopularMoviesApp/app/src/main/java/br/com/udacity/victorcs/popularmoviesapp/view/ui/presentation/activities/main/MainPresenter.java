package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.interactor.IMainInteractor;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.rx.ComposesRx;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends MainContract.Presenter {

    private IMainInteractor iMainInteractor;
    private MainContract.View view;

    @Inject
    public MainPresenter(MainContract.View view, IMainInteractor iMainInteractor) {
        super(view);
        this.view = view;
        this.iMainInteractor = iMainInteractor;
    }

    @Override
    void onCreate() {
        view.initComponent();
    }

    @SuppressLint("CheckResult")
    @Override
    void getPopularMovies(int index) {
        try {
            view.showProgress();
            iMainInteractor.getPopularMoviesList(index)
                    .compose(ComposesRx.applyMaybeSchedulers())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> view.hideProgress())
                    .subscribe(
                            movies -> view.setMoviesListIntoRecyclerView(movies),
                            error -> view.showMessage(error.getMessage())
                    );
        } catch (Exception ex) {
            TimberHelper.e(Constants.ERROR, "getPopularMovies " + ex.toString());
        }
    }

    @SuppressLint("CheckResult")
    @Override
    void getTopRatedMovies(int index) {
        try {
            view.showProgress();
            iMainInteractor.getTopRatedMoviesList(index)
                    .compose(ComposesRx.applyMaybeSchedulers())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> view.hideProgress())
                    .subscribe(
                            movies -> view.setMoviesListIntoRecyclerView(movies),
                            error -> view.showMessage(error.getMessage())
                    );
        } catch (Exception ex) {
            TimberHelper.e(Constants.ERROR, "getPopularMovies " + ex.toString());
        }
    }

}
