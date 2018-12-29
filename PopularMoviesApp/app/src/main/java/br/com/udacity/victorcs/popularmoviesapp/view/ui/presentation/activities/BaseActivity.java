package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initializePresenter();

    protected abstract void setupFlux();

    @Override
    protected void onStart() {
        super.onStart();
        initializePresenter();
        setupFlux();
    }

}
