package com.appetiser.ituneflix.pages.home.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appetiser.ituneflix.AppConstants;
import com.appetiser.ituneflix.AppSessions;
import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.helper.KeyboardHelper;
import com.appetiser.ituneflix.pages.detail.DetailedViewActivity;
import com.appetiser.ituneflix.views.IFTextView;
import com.appetiser.ituneflix.views.SearchTextWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchListActivity extends Activity implements SearchListAdapter.SelectMovieListener, SearchListViewInterface {

    /**
     * Bind Views much faster and
     * simplier than the traditional
     * findviewbyid
     * thanks to butterknife
     */
    @BindView(R.id.movie_list)
    RecyclerView movieList;
    @BindView(R.id.text_search)
    SearchTextWatcher searchText;
    @BindView(R.id.image_cancel)
    ImageView cancelImage;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.empty_list)
    IFTextView emptyList;
    @BindView(R.id.recent_layout)
    RelativeLayout recentlyLayout;
    @BindView(R.id.recently_text)
    IFTextView recentlyText;

    /**
     * This movie will be our featured movie
     * and the contents will be displayed in the list header
     */
    private Movie featureMovie;


    /**
     * When the user did not type for about 1 second.
     * get a new list from api
     */
    private int SEARCH_THRESHOLD_IN_MILLIS = 1000;
    private int TICK_INTERVAL_IN_MILLS = 1000;

    private CountDownTimer countDownTimer;


    /**
     * Declare variables
     */
    private List<Movie> listMovies;
    private SearchListAdapter topListAdapter;
    private LinearLayoutManager layoutManager;

    SearchListActivityPresenter presenter;


    public SearchListActivity() {
        listMovies = new ArrayList<>();
        featureMovie = new Movie();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);

        initLayoutManagers();
        initAdapter(listMovies);
        initializePresenter();
        loadMoviesFromDB();
        initSearch();
        checkPageRedirection();

    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * Page Tracker meaning user is in the Main Page
         * or Search Page
         */
        AppSessions.saveLastActivity(AppConstants.MAIN_PAGE);
    }

    /**
     * Setup layout manager
     * This will determine
     * what kind of list to show
     * in this case its linear
     */
    private void initLayoutManagers() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        movieList.setLayoutManager(layoutManager);
    }

    /**
     * Initialize Adapter
     */
    private void initAdapter(List<Movie> moviesList) {

        if (topListAdapter == null) {
            topListAdapter = new SearchListAdapter(moviesList, this, this);
        }

        movieList.setAdapter(topListAdapter);
        topListAdapter.notifyDataSetChanged();
    }


    /**
     * Initializing presenter
     */
    private void initializePresenter() {
        presenter = new SearchListActivityPresenter(this);
    }

    /**
     * Load Movies from API Requeset
     */
    private void loadMovies() {
        presenter.getListOfMovies(searchText.getText().toString());
    }


    /**
     * On App launch load movies from DB
     */
    private void loadMoviesFromDB() {
        presenter.getListOfMoviesFromDB(searchText.getText().toString());
    }

    /**
     * Check for page redirection
     * and if page is redirected
     * get the last Track ID stored in session
     */
    private void checkPageRedirection() {
        if (getIntent().getBooleanExtra("proceedToDetailView", false)) {
            if (AppSessions.restoreLastTrackID() != -1)
                goToDetails(AppSessions.restoreLastTrackID());
        }
    }

    /**
     * Selected movies will be viewed
     * in our Detail View Page
     *
     * @param movie
     * @param position
     */
    @Override
    public void select(Movie movie, int position) {
        goToDetails(movie.trackId);
    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        hideLoadingBar();
        listMovies.clear();
        listMovies.addAll(movieList);
        topListAdapter.notifyDataSetChanged();
        checkEmptyList();
    }

    /**
     * When network error occur
     * or no list
     */
    @Override
    public void displayError() {
        hideLoadingBar();
        listMovies.clear();
        topListAdapter.notifyDataSetChanged();
        checkEmptyList();
    }

    @OnClick(R.id.empty_list)
    void refresh() {
        loadMovies();
    }

    /**
     * If search bar is cleared
     * we load list save in our DB
     */
    @OnClick(R.id.image_cancel)
    void cancel() {
        searchText.setText("");
        loadMoviesFromDB();
    }

    /**
     * Proceed to detailed view
     *
     * @param trackId
     */
    private void goToDetails(int trackId) {
        Intent intent = new Intent(this, DetailedViewActivity.class);
        intent.putExtra("trackId", trackId);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

    }

    /**
     * hide loading bar
     */
    private void hideLoadingBar() {
        cancelImage.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Show loading bar
     */
    private void showLoadingBar() {
        cancelImage.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Check if listmovies are empty
     * if Empty display empty list error
     * if not display recently last search label
     */
    private void checkEmptyList() {
        recentlyLayout.setVisibility(View.GONE);
        if (listMovies.isEmpty()) {
            emptyList.setVisibility(View.VISIBLE);
        } else {
            emptyList.setVisibility(View.GONE);
            if (AppSessions.restoreLastSearchDate() != null) {
                recentlyLayout.setVisibility(View.VISIBLE);
                recentlyText.setText("Recently searched movies last " + AppSessions.restoreLastSearchDate());
            }
        }
    }

    /**
     * Initialize search view
     */
    private void initSearch() {

        searchText.setOnEditorActionListener(searchKeyListener);

        searchText.watcher(new SearchTextWatcher.Listener() {
            @Override
            public void onLoadFromDB(String text) {
            }

            @Override
            public void onLoadFromAPI() {
                /**
                 * If search text is not empty
                 * then load list coming from API
                 */
                if (!searchText.getText().toString().isEmpty()) {
                    showLoadingBar();
                    loadMovies();
                }
            }
        });

    }

    /**
     * Hide key board after clicking search
     */
    TextView.OnEditorActionListener searchKeyListener = (v, actionId, event) -> {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            KeyboardHelper.hideKeyboard(v, this);
            return true;
        }
        return false;
    };


}
