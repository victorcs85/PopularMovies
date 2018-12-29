package br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.detail;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.udacity.victorcs.popularmoviesapp.R;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.domain.Constants;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.receiver.Movie;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.activities.BaseActivity;
import br.com.udacity.victorcs.popularmoviesapp.view.ui.presentation.logs.TimberHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by victorcs on 12/18/2018.
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {

    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.tvMovieTitle)
    TextView tvMovieTitle;
    @BindView(R.id.ivMovie)
    ImageView ivMovie;
    @BindView(R.id.tvYear)
    TextView tvYear;
    @BindView(R.id.tvRating)
    TextView tvRating;
    @BindView(R.id.tvSynopsis)
    TextView tvSynopsis;

    private MovieDetailPresenter detailPresenter;
    private Movie selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        verifyMovieExtra();
    }

    private void verifyMovieExtra() {
        selectedMovie = (Movie) getIntent().getSerializableExtra(Constants.MOVIE_DETAIL);
    }

    @Override
    protected void initializePresenter() {
        detailPresenter = new MovieDetailPresenter();
        detailPresenter.setView(this);
        detailPresenter.onCreate();
    }

    @Override
    protected void setupFlux() {
        detailPresenter.setSelectedMovie(selectedMovie);
    }

    @Override
    public void setupComponents(String movieTitle, String movieImage,
                                String movieYear, String movieRating, String movieSynopsis) {
        try {
            tvMovieTitle.setText(movieTitle);
            Glide.with(MovieDetailActivity.this).load(movieImage).into(ivMovie);
            tvYear.setText(movieYear);
            tvRating.setText(getString(R.string.movie_average, movieRating ));
            tvSynopsis.setText(movieSynopsis);
        } catch (Exception ex) {
            TimberHelper.e(Constants.ERROR, ex.toString());
            String errorMessage = ex.getMessage() == null ? "" : ex.getMessage();
            detailPresenter.callShowMessage(errorMessage);
        }
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
        tvMovieTitle.setVisibility(View.GONE);
        ivMovie.setVisibility(View.GONE);
        tvYear.setVisibility(View.GONE);
        tvRating.setVisibility(View.GONE);
        tvSynopsis.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
        tvMovieTitle.setVisibility(View.VISIBLE);
        ivMovie.setVisibility(View.VISIBLE);
        tvYear.setVisibility(View.VISIBLE);
        tvRating.setVisibility(View.VISIBLE);
        tvSynopsis.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> {
                    dialog.dismiss();
                }).create();
        alertDialog.show();
    }

}
