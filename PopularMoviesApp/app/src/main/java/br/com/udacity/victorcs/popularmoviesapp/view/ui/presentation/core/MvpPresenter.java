package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.core;

import dagger.Lazy;
import io.reactivex.disposables.CompositeDisposable;

public abstract class MvpPresenter<T extends BaseView> {

    private Lazy<CompositeDisposable> disposable = () -> {
        CompositeDisposable val = new CompositeDisposable();
        disposable = () -> val;
        return val;
    };

    private T view = null;

    public void onAttach(T view) {
        this.view = view;
    }

    public void onDetach() {
        disposable.get().clear();
        this.view = null;
    }
}
