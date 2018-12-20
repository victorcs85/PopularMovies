package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main.di;

import javax.inject.Singleton;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.interactor.IMainInteractor;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.interactor.MainInteractor;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main.MainActivity;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main.MainContract;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by victorcs on 12/15/2018.
 */
@Module
public class MainPresenterModule {
    @Provides
    @Singleton
    static MainContract.Presenter provideMainPresenter(MainContract.View view, IMainInteractor iMainInteractor) {
        return new MainPresenter(view, iMainInteractor);
    }
}
