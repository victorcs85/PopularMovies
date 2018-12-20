package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.di;

import javax.inject.Singleton;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.interactor.IMainInteractor;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.interactor.MainInteractor;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository.IMoviesRepository;
import dagger.Module;
import dagger.Provides;
/**
 * Created by victorcs on 12/13/2018.
 */
@Module
public class MainModule {
    @Provides
    @Singleton
    static IMainInteractor provideMainInteractor(IMoviesRepository iMoviesRepository) {
        return new MainInteractor(iMoviesRepository);
    }
}
