package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.com.udacity.victorcs.popularmoviesapp.R;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.BaseActivity;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.detail.MovieDetailActivity;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.settings.SettingsActivity;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.listeners.EndlessRecyclerViewScrollListener;
import butterknife.BindView;

/**
 * Created by victorcs on 12/15/2018.
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String order = preferences.getString("order_movies", Constants.MOST_POPULAR);
        if(order == null || order.equalsIgnoreCase(Constants.MOST_POPULAR)) {
            //TODO
        }
        //TODO - presenter
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initComponent() {
        rvMovies.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMovies.setLayoutManager(gridLayoutManager);
        rvMovies.setItemAnimator(new DefaultItemAnimator());

        rvMovies.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvMovies, new
                RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        MoviesAdapter adapter = (MoviesAdapter) rvMovies.getAdapter();
                        if (adapter != null) {
                            ArrayList<Movie> moviesList = adapter.getMoviesList();
                            Movie movie = moviesList.get(position);
                            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                            intent.putExtra(Constants.MOVIE_DETAIL, movie);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

        EndlessRecyclerViewScrollListener listener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                String order = preferences.getString("order_movies", Constants.MOST_POPULAR);
                //TODO
                if(order == null || order.equalsIgnoreCase(Constants.MOST_POPULAR)){
//                    presenter.getPopularMovies(page);
                } else {
//                    presenter.getTopRatedMovies(page);
                }
            }
        };

        rvMovies.addOnScrollListener(listener);
    }

    @Override
    public void setMoviesListIntoRecyclerView(ArrayList<Movie> movies) {
        MoviesAdapter adapter = new MoviesAdapter(MainActivity.this, movies);
        rvMovies.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        rvMovies.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rvMovies.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> {
                    //TODO - efetuar nova chamada com o última posição do scroll
                    dialog.dismiss();
                }).create();
        alertDialog.show();
    }
}
