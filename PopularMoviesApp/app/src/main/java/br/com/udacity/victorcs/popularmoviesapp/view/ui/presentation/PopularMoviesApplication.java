package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation;

import android.app.Application;

import br.com.udacity.victorcs.popularmoviesapp.BuildConfig;
import timber.log.Timber;
/**
 * Created by victorcs on 12/13/2018.
 */
public class PopularMoviesApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
