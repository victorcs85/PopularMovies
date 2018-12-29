package br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.com.udacity.victorcs.popularmoviesapp.BuildConfig;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by victorcs on 12/13/2018.
 */
public class RetrofitFactory {

    public RetrofitFactory() {}

    public Retrofit baseRequest() {
        if (!ConnectionUtils.isInternetAvailable()) {
            try {
                throw new NetworkException(Constants.MSG_NO_INTERNET);
            } catch (NetworkException e) {
                e.printStackTrace();
            }
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message ->
                TimberHelper.i("OkHttp", message));

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        PopularHeaders headersInterceptor = new PopularHeaders();

        OkHttpClient.Builder build = new OkHttpClient.Builder();
        long timeoutSecs = 35L;
        build.connectTimeout(timeoutSecs, TimeUnit.SECONDS)
                .writeTimeout(timeoutSecs, TimeUnit.SECONDS)
                .readTimeout(timeoutSecs, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headersInterceptor);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder retroBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io()))
                .baseUrl(BuildConfig.API_URL)
                .client(build.build());

        return retroBuilder.build();
    }
}
