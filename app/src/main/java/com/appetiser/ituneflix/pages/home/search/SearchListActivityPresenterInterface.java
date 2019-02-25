package com.appetiser.ituneflix.pages.home.search;

public interface SearchListActivityPresenterInterface {


    /**
     * This will list top movies
     * either from DB or from API Request
     */
    void getListOfMovies(String search);
    void getListOfMoviesFromDB(String search);
}
