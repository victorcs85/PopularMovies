package br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.retrofit;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.BuildConfig;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.repository.IMoviesRepository;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.BaseRetrofit;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.RetrofitFactory;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.TheMovieApi;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.error.MovieError;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.MoviesResult;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import io.reactivex.Maybe;
import retrofit2.Response;

public class MoviesRetrofit extends BaseRetrofit implements IMoviesRepository {

    public MoviesRetrofit() {
        this.retrofitFactory = new RetrofitFactory();
    }

    RetrofitFactory retrofitFactory;

    @Override
    public Maybe<ArrayList<Movie>> getPopularMoviesList(int index) {
        return Maybe.create(emitter -> {
            try {
                Response<MoviesResult> response = retrofitFactory
                        .baseRequest()
                        .create(TheMovieApi.class)
                        .getPopularList(BuildConfig.API_KEY, index)
                        .execute();

                if (!checkError(response, emitter)) {

                    ArrayList<Movie> movies;
                    if (response.body() != null && response.body().getMovies() != null) {
                        movies = (ArrayList<Movie>) response.body().getMovies();
                        emitter.onSuccess(movies);
                    } else {
                        emitter.onError(new MovieError("Não há filmes populares!"));
                    }

                }
            } catch (Exception ex) {
                TimberHelper.e(Constants.ERROR, "getPopularMoviesList " + ex.toString());
                emitter.onError(new MovieError("Ocorreu um erro ao buscar os filmes populares!"));
            }
        });
    }

    @Override
    public Maybe<ArrayList<Movie>> getTopRatedMoviesList(int index) {
        return Maybe.create(emitter -> {
            try {
                Response<MoviesResult> response = retrofitFactory
                        .baseRequest()
                        .create(TheMovieApi.class)
                        .getTopRatedList(BuildConfig.API_KEY, index)
                        .execute();

                if (!checkError(response, emitter)) {

                    ArrayList<Movie> movies;
                    if (response.body() != null && response.body().getMovies() != null) {
                        movies = (ArrayList<Movie>) response.body().getMovies();
                        emitter.onSuccess(movies);
                    } else {
                        emitter.onError(new MovieError("Não há filmes tops!"));
                    }

                }
            } catch (Exception ex) {
                TimberHelper.e(Constants.ERROR, "getPopularMoviesList " + ex.toString());
                emitter.onError(new MovieError("Ocorreu um erro ao buscar os filmes tops!"));
            }
        });
    }
}
