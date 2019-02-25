package com.appetiser.ituneflix.db;

import com.appetiser.ituneflix.api.models.movies.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;

public class MoviesDB {


    /**
     * Get last searched movies with no filter
     *
     * @return
     */
    public static List<Movie> getTopMoviesList() {
        try {
            Realm realm = Realm.getDefaultInstance();
            return (realm.where(Movie.class).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Get last searched movies with filter
     * or the user searches for a certain image
     *
     * @return
     */
    public static List<Movie> getTopMoviesListWithFilter(String search) {
        try {
            Realm realm = Realm.getDefaultInstance();
            return (realm.where(Movie.class).contains("trackName", search, Case.INSENSITIVE).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * Open realm transaction
     * then delete all search list of movies
     * then save new one.
     *
     * @param movies
     */
    public static void saveAllTopList(List<Movie> movies) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.copyToRealmOrUpdate(movies);
        realm.commitTransaction();

    }

    /**
     * Get a single movie. or selected movie
     *
     * @param trackID
     * @return
     */
    public static Movie getSingleMovie(int trackID) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Movie account = realm.where(Movie.class).equalTo("trackId", trackID).findFirst();
        realm.commitTransaction();
        return account;

    }


}
