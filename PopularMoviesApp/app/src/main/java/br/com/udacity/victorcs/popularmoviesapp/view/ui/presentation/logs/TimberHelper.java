package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import timber.log.Timber;

/**
 * Created by victorcs on 12/13/2018.
 */
public abstract class TimberHelper {

    public static void v(String identifier, String message) {
        Timber.tag(Constants.INFO).v(identifier + ": " + message);
    }

    public static void i(String identifier, String message) {
        Timber.tag(Constants.INFO).i(identifier + ": " + message);
    }

    public static void e(String identifier, String message) {
        Timber.tag(Constants.ERROR).e(identifier + ": " + message);
    }

    public static void w(String identifier, String message) {
        Timber.tag(Constants.ERROR).w(identifier + ": " + message);
    }

}
