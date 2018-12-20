package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.di;

import javax.inject.Singleton;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository.IMoviesRepository;
import dagger.BindsInstance;
import dagger.Component;
/**
 * Created by victorcs on 12/13/2018.
 */
@Singleton
@Component(modules = MoviesModule.class)
public interface MoviesComponent {
    void inject(MoviesModule moviesModule);
}
