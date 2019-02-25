package com.appetiser.ituneflix.pages.home.search;

import com.appetiser.ituneflix.api.models.movies.Movie;

import java.util.List;

public interface SearchListViewInterface {

    /**
     * This will display the list of movies
     */
    void displayMovies(List<Movie> movieList);
    void displayError();

}
