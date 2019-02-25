package com.appetiser.ituneflix.pages.home.top;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.api.models.movies.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopListFragment extends Fragment implements TopListAdapter.SelectMovieListener, TopListViewInterface {

    /**
     * Bind Views
     */
    @BindView(R.id.movie_list)
    RecyclerView movieList;

    /**
     * Declare variables
     */
    private List<Movie> listMovies;
    private RecyclerView.Adapter topListAdapter;
    private LinearLayoutManager layoutManager;

    TopListFragmentPresenter presenter;


    public TopListFragment() {
        listMovies = new ArrayList<>();
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
            topListAdapter = new TopListAdapter(listMovies, this);
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
    public void displayMovies(List<Movie> movieList) {
        Log.e("AAAss", "AAAAA" + movieList.size());
        listMovies.clear();
        listMovies.addAll(movieList);
        topListAdapter.notifyDataSetChanged();
    }
}
