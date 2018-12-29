package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
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
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by victorcs on 12/15/2018.
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    public static final String KEY_FOR_LAYOUT_MANAGER_STATE = "KeyForLayoutManagerState";
    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    MainPresenter mainPresenter;

    private SharedPreferences preferences;

    private int currentListIndex = 1;
    private boolean isLoadByScroll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initializePresenter() {
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
        mainPresenter.onCreate();
    }

    @Override
    protected void setupFlux() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isLoadByScroll = false;
        verifyCallMovie(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDetach();
    }

    @Override
    public void initComponent() {
        rvMovies.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMovies.setLayoutManager(gridLayoutManager);

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

        rvMovies.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                currentListIndex = page;
                if (currentListIndex > 1) {
                    isLoadByScroll = true;
                }
                verifyCallMovie(true);
            }
        });

       /* rvMovies.post(() -> {
            if (rvMovies.getAdapter() != null) {
                rvMovies.getAdapter().notifyItemInserted(
                        ((MoviesAdapter) rvMovies.getAdapter()).getMoviesList().size() - 1);
            }
        });*/

    }

    @Override
    public void setMoviesListIntoRecyclerView(ArrayList<Movie> movies) {
        MoviesAdapter adapter = new MoviesAdapter(MainActivity.this, movies);
        rvMovies.setAdapter(adapter);
    }

    @Override
    public void addNewMoviesListIntoRecyclerView(ArrayList<Movie> movies) {
        if (rvMovies.getAdapter() != null) {
            ((MoviesAdapter) rvMovies.getAdapter()).addItems(movies);
        }
    }

    @Override
    public void showProgress() {
        if (!isLoadByScroll) {
            rvMovies.setVisibility(View.GONE);
            pbLoading.setVisibility(View.VISIBLE);
        }
        TimberHelper.e(Constants.ERROR, isLoadByScroll+" +++++++++++++");
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
        rvMovies.setVisibility(View.VISIBLE);
        isLoadByScroll = false;
    }

    @Override
    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> {
                    verifyCallMovie(true);
                    dialog.dismiss();
                }).create();
        alertDialog.show();
    }

    @Override
    public void verifyCallMovie(boolean loadMoreItems) {
        String order = preferences.getString("order_movies", Constants.MOST_POPULAR);
        if (order == null || order.equalsIgnoreCase(Constants.MOST_POPULAR)) {
            mainPresenter.getPopularMovies(currentListIndex, loadMoreItems);
        } else {
            mainPresenter.getTopRatedMovies(currentListIndex, loadMoreItems);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (rvMovies != null && rvMovies.getLayoutManager() != null) {
            outState.putParcelable(KEY_FOR_LAYOUT_MANAGER_STATE, rvMovies.getLayoutManager().onSaveInstanceState());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (rvMovies != null && rvMovies.getLayoutManager() != null) {
            Parcelable parcelable = savedInstanceState.getParcelable(KEY_FOR_LAYOUT_MANAGER_STATE);
            rvMovies.getLayoutManager().onRestoreInstanceState(parcelable);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        rvMovies.requestLayout();
        if (rvMovies.getAdapter() != null) {
            rvMovies.getAdapter().notifyDataSetChanged();
        }
    }
}
