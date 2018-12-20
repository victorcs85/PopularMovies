package br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network;

import java.io.IOException;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by victorcs on 12/13/2018.
 */
public class PopularHeaders  implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .addHeader(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .addHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON_CHARSET_UTF_8);

        Request request = null;
        try {
            request = requestBuilder.build();
        } catch (Exception e) {
            TimberHelper.e("requestBuilder.build()", e.toString());
        }

        return chain.proceed(request);
    }
}