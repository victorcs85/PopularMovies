package br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.rx;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import io.reactivex.Maybe;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
 * Created by victorcs on 12/13/2018.
 */
public class ComposesRx {

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(
                        it -> {
                            if (it instanceof Throwable) {
                                TimberHelper.e(Constants.ERROR, it.toString());
                                Observable.error((Throwable) it);
                            }
                        });
    }

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(
                                it -> {
                                    TimberHelper.e(Constants.ERROR, it.toString());
                                    return Single.error(it);
                                }
                        );
    }

    public static <T> MaybeTransformer<T, T> applyMaybeSchedulers() {
        return upstream ->
                        upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .onErrorResumeNext(
                                        it -> {
                                            if (it instanceof Throwable) {
                                                TimberHelper.e(Constants.ERROR, it.toString());
                                                Maybe.error((Throwable) it);
                                            }
                                        });
    }

}
