package com.appetiser.ituneflix.pages.home.top;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.db.MoviesDB;
import com.appetiser.ituneflix.views.IFTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopListFragment extends Fragment implements TopListAdapter.SelectMovieListener, TopListViewInterface {

    /**
     * Bind Views
     */
    @BindView(R.id.movie_list)
    RecyclerView movieList;
    @BindView(R.id.empty_list)
    IFTextView emptyList;
    @BindView(R.id.loading_view)
    RelativeLayout loading;
    @BindView(R.id.image_header)
    ImageView headerImage;
    @BindView(R.id.header_title_text)
    IFTextView titleTextHeader;
    @BindView(R.id.header_genre_text)
    IFTextView genreTextHeader;
    @BindView(R.id.header_price_text)
    IFTextView priceTextHeader;
    @BindView(R.id.mylist_image)
    ImageView myListImage;
    @BindView(R.id.top_tab_layout)
    CoordinatorLayout topTabLayout;

    /**
     * This movie will be our featured movie
     * and the contents will be displayed in the list header
     */
    private Movie featureMovie;


    /**
     * Declare variables
     */
    private List<Movie> listMovies;
    private TopListAdapter topListAdapter;
    private LinearLayoutManager layoutManager;

    TopListFragmentPresenter presenter;


    public TopListFragment() {
        listMovies = new ArrayList<>();
        featureMovie = new Movie();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_fragment, container, false);
        /**
         * Initialize binding
         */
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initLayoutManagers();
        initAdapter();
        initializePresenter();
        loadMovies();

    }


    /**
     * Setup layout manager
     * This will determine
     * what kind of list to show
     * in this case its linear
     */
    private void initLayoutManagers() {
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        movieList.setLayoutManager(layoutManager);
    }

    /**
     * Initialize Adapter
     */
    private void initAdapter() {

        if (topListAdapter == null) {
            topListAdapter = new TopListAdapter(listMovies, this, getActivity());
        }

        movieList.setAdapter(topListAdapter);
        topListAdapter.notifyDataSetChanged();
    }

    /**
     * Initializing presenter
     */
    private void initializePresenter() {
        presenter = new TopListFragmentPresenter(this);
    }

    /**
     * Load Movies from DB
     * or From API Request
     */
    private void loadMovies() {
        /**
         * Show  loader
         */
        loading.setVisibility(View.VISIBLE);
        presenter.getListOfMovies();
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

    }

    @Override
    public void mylist(Movie movie, int position) {

        /**
         * Update movie favorite status
         * Update the selected movie first from DB
         * Get that movie and then set that new movie
         * to be displayed in the list
         */
        MoviesDB.updateMovieMyListStatus(movie, movie.favorite);
        Movie updatedMove = MoviesDB.getSingleMovie(movie.trackId);
        listMovies.set(position, updatedMove);
        topListAdapter.notifyDataSetChanged();

    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        /**
         * Hide Loader
         */
        loading.setVisibility(View.GONE);

        listMovies.clear();
        listMovies.addAll(movieList);
        checkEmptyList();
        getFeaturedMovie();
        topListAdapter.notifyDataSetChanged();
        if (featureMovie != null)
            displayFeaturedMovie();

    }

    @Override
    public void displayError() {
        checkEmptyList();
    }

    @OnClick(R.id.empty_list)
    void refresh() {
        loadMovies();
    }

    /**
     * THis will change favorite status of feature movie
     */
    @OnClick(R.id.mylist_layout)
    void myList() {

        /**
         * Update featured movie favorite status
         * Update the selected movie first from DB
         * Get that movie and then display new movie status
         */
        MoviesDB.updateMovieMyListStatus(featureMovie, featureMovie.favorite);
        featureMovie = MoviesDB.getSingleMovie(featureMovie.trackId);
        setFavoriteStatusImage();

    }

    private void getFeaturedMovie() {

        if (listMovies.isEmpty())
            return;

        Random random = new Random();
        int indexSelected = random.nextInt(listMovies.size());
        featureMovie = listMovies.get(indexSelected);
        listMovies.remove(indexSelected);

    }

    private void displayFeaturedMovie() {

        topTabLayout.setVisibility(View.VISIBLE);
        Glide.with(this).load(featureMovie.artworkUrl100).apply(new RequestOptions().override(200, 200)).into(headerImage);
        priceTextHeader.setText("" + featureMovie.trackPrice + " " + featureMovie.currency);
        titleTextHeader.setText("" + featureMovie.trackName);
        genreTextHeader.setText("" + featureMovie.primaryGenreName);
        setFavoriteStatusImage();

    }

    private void setFavoriteStatusImage() {
        if (featureMovie.favorite)
            myListImage.setImageResource(R.drawable.ic_success);
        else
            myListImage.setImageResource(R.drawable.ic_plus);
    }

    private void checkEmptyList() {
        if (listMovies.isEmpty())
            emptyList.setVisibility(View.VISIBLE);
        else
            emptyList.setVisibility(View.GONE);
    }
}
