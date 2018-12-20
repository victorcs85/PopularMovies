package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.di;

import javax.inject.Singleton;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository.IMoviesRepository;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.retrofit.MoviesRetrofit;
import dagger.Module;
import dagger.Provides;
/**
 * Created by victorcs on 12/15/2018.
 */
@Module
public class MoviesModule {
    @Provides
    @Singleton
    static IMoviesRepository provideMoviesRepository() {
        return new MoviesRetrofit();
    }
}
