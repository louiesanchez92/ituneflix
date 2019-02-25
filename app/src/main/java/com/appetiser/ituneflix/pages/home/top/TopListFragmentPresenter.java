package com.appetiser.ituneflix.pages.home.top;

import com.appetiser.ituneflix.api.models.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public class TopListFragmentPresenter implements TopListFragmentPresenterInterface {


    TopListViewInterface tvi;

    public TopListFragmentPresenter(TopListViewInterface tvi) {
        this.tvi = tvi;
    }

    public void getMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        tvi.displayMovies(movieList);
    }


    @Override
    public void getListOfMovies() {
        getMovies();
    }


}
