package br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.di;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by victorcs on 12/13/2018.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainModule mainModule);
}
