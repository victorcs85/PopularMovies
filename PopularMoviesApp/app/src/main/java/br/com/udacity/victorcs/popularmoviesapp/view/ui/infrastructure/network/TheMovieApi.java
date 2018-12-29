package br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network;

import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.MoviesResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by victorcs on 12/13/2018.
 */
public interface TheMovieApi {

    @GET("movie/popular")
    Call<MoviesResult> getPopularList(@Query("api_key") String user_key,
                                      @Query("page") int pageNumber);

    @GET("movie/top_rated")
    Call<MoviesResult> getTopRatedList(@Query("api_key") String user_key,
                                             @Query("page") int pageNumber);

}
