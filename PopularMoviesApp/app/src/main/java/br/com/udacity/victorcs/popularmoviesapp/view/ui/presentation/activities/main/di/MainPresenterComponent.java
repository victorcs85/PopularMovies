package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main.di;

import javax.inject.Singleton;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.di.MainModule;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main.MainPresenter;
import dagger.Component;

/**
 * Created by victorcs on 12/15/2018.
 */
@Singleton
@Component(modules = MainPresenterModule.class)
public interface MainPresenterComponent {
    void inject(MainPresenterModule mainModule);
}
