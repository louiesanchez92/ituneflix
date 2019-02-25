package com.appetiser.ituneflix.pages.home.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.db.MoviesDB;
import com.appetiser.ituneflix.views.IFTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyListFragment extends Fragment implements MyListAdapter.SelectMovieListener {

    @BindView(R.id.movie_list)
    RecyclerView movieList;
    @BindView(R.id.empty_list)
    IFTextView emptyList;

    private List<Movie> listMovies;
    private MyListAdapter myListAdapter;
    private LinearLayoutManager layoutManager;

    public MyListFragment() {
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
        View view = inflater.inflate(R.layout.mylist_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initLayoutManagers();
        initAdapter();
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

        if (myListAdapter == null) {
            myListAdapter = new MyListAdapter(listMovies, this, getActivity());
        }

        movieList.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();
    }

    private void loadMovies() {
        listMovies.clear();
        listMovies.addAll(MoviesDB.getFavoriteMovies());
        myListAdapter.notifyDataSetChanged();
        checkEmptyList();
    }

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
        listMovies.remove(position);
        myListAdapter.notifyDataSetChanged();
        checkEmptyList();

    }

    private void checkEmptyList() {
        if (listMovies.isEmpty()) {
            emptyList.setVisibility(View.VISIBLE);
        } else {
            emptyList.setVisibility(View.GONE);
        }
    }
}
