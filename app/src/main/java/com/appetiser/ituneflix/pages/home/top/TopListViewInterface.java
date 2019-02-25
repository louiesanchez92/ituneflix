package com.appetiser.ituneflix.pages.home.top;

import com.appetiser.ituneflix.api.models.movies.Movie;

import java.util.List;

public interface TopListViewInterface {

    /**
     * This will display the list of movies
     */
    void displayMovies(List<Movie> movieList);
    void displayError();

}
