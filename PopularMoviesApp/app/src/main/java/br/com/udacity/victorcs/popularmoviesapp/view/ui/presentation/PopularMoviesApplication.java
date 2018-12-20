package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation;

import android.app.Application;

import br.com.udacity.victorcs.popularmoviesapp.BuildConfig;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.di.MainComponent;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.di.MoviesComponent;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main.di.MainPresenterComponent;
import timber.log.Timber;
/**
 * Created by victorcs on 12/13/2018.
 */
public class PopularMoviesApplication extends Application {

    MainComponent mMainComponent;
    MoviesComponent mMoviesComponent;
    MainPresenterComponent mMainPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
//        mMainComponent = createMainComponent();
//        mMoviesComponent = createMoviesComponent();
//        mMainPresenterComponent = createMainPresenterComponent();
    }

  /*  MainComponent getMainComponent() {
        return mMainComponent;
    }

    private MainComponent createMainComponent() {
        return DaggerMainComponent
                .builder()
                .mainModule(new MainModule())
                .build();
    }

    MoviesComponent getMoviesComponent() {
        return mMoviesComponent;
    }

    private MoviesComponent createMoviesComponent() {
        return DaggerMoviesComponent
                .builder()
                .moviesModule(new MoviesModule())
                .build();
    }


    MainPresenterComponent getMainPresenterComponent() {
        return mMainPresenterComponent;
    }

    private MainPresenterComponent createMainPresenterComponent() {
        return DaggerMainPresenterComponent
                .builder()
                .mainPresenterModule(new MainPresenterModule())
                .build();
    }*/
}
