package com.appetiser.ituneflix.pages.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.appetiser.ituneflix.AppConstants;
import com.appetiser.ituneflix.AppSessions;
import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.db.MoviesDB;
import com.appetiser.ituneflix.views.IFTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailedViewActivity extends AppCompatActivity {

    /**
     * Bind Views
     */
    @BindView(R.id.image_movie)
    ImageView movieImage;
    @BindView(R.id.title_text)
    IFTextView titleText;
    @BindView(R.id.genre_text)
    IFTextView genreText;
    @BindView(R.id.price_text)
    IFTextView priceText;
    @BindView(R.id.description)
    IFTextView description;

    private Movie movie;

    public DetailedViewActivity() {
        movie = new Movie();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        /**
         * Initialize binding
         */
        ButterKnife.bind(this);

        /**
         * Get Movie selected from DB
         */
        movie = MoviesDB.getSingleMovie(getIntent().getIntExtra("trackId", -1));
        loadMovie();

    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * Page Tracker meaning user is in the Main Page
         * or Search Page
         *
         * save track ID also
         */
        AppSessions.saveLastActivity(AppConstants.DETAIL_PAGE);
        AppSessions.saveLastTrackID(getIntent().getIntExtra("trackId", -1));
    }

    @OnClick(R.id.back_layout)
    void back() {
        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }

    private void loadMovie() {
        /**
         * Loads movies data
         * like movie artwork, price, currency, trackname
         * and genre
         */
        Glide.with(this).load(movie.artworkUrl100).apply(new RequestOptions().override(200, 200)).into(movieImage);

        priceText.setText("" + movie.trackPrice + " " + movie.currency);

        titleText.setText("" + movie.trackName);

        genreText.setText("" + movie.primaryGenreName);

        description.setText(movie.longDescription);
    }

}
