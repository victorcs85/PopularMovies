package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.core;

import dagger.Lazy;
import io.reactivex.disposables.CompositeDisposable;

public class MvpPresenter<T extends BaseView> implements BasePresenter {

    private Lazy<CompositeDisposable> disposable = () -> {
        CompositeDisposable val = new CompositeDisposable();
        disposable = () -> val;
        return val;
    };

    private T view = null;

    public MvpPresenter(T view) {
        this.view = view;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        disposable.get().clear();
        this.view = null;
    }
}
