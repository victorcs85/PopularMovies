package br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketException;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.error.MovieError;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import io.reactivex.CompletableEmitter;
import io.reactivex.MaybeEmitter;
import io.reactivex.SingleEmitter;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Response;

public abstract class BaseRetrofft {

    private Boolean errorFound = false;

    public Boolean checkError(Response response, Object emitter) {

        MovieError movieError = null;

        try {
            if (!response.isSuccessful() && response.errorBody() != null) {
                Exception objError = new Gson().fromJson(response.errorBody().toString(), Exception.class);
                if (objError.getMessage() != null) {
                    movieError = new MovieError(objError.getMessage());
                    errorFound = true;
                } else {
                    movieError = new MovieError(Constants.MSG_NO_INTERNET);
                    errorFound = true;
                }

            }

        } catch (Exception e) {
            TimberHelper.e(Constants.ERROR, "" + e.getMessage());
            movieError = new MovieError(e.getMessage());
            errorFound = true;
        }

        if (movieError != null) {
            if (emitter instanceof SingleEmitter) {
                ((SingleEmitter) emitter).onError(movieError);
            } else if (emitter instanceof CompletableEmitter) {
                ((CompletableEmitter) emitter).onError(movieError);
            } else if (emitter instanceof MaybeEmitter) {
                ((MaybeEmitter) emitter).onError(movieError);
            }
        }
        return errorFound;
    }

    public Boolean checkRetrofitError(Response response) {

        RxJavaPlugins.setErrorHandler(
                e -> {

                    Throwable ex;

                    try {
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            Exception objError = new Gson().fromJson(response.errorBody().toString(), Exception.class);
                            if (objError.getMessage() != null) {
                                TimberHelper.e(Constants.ERROR, objError.getMessage());
                            } else {
                                TimberHelper.e(Constants.ERROR, Constants.MSG_NO_INTERNET);
                            }
                            errorFound = true;
                        }

                    } catch (Exception exc) {
                        errorFound = true;
                        TimberHelper.e(Constants.ERROR, "" + exc.getMessage());
                    }

                    if (e != null) {
                        errorFound = true;
                        TimberHelper.w(Constants.WARNING, e.toString());
                        if (e instanceof UndeliverableException) {
                            ex = e.getCause();
                            TimberHelper.w(Constants.WARNING, "Undeliverable exception received, not sure what to do " + ex.toString());
                        } else if (e instanceof NullPointerException
                                || e instanceof IllegalArgumentException
                                || e instanceof IllegalStateException) {
                            Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                        }
                    }
                }

        );

        return errorFound;

    }

}
