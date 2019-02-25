package com.appetiser.ituneflix.db;

import com.appetiser.ituneflix.api.models.movies.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MoviesDB {


    public static List<Movie> getTopMoviesList() {
        try {
            Realm realm = Realm.getDefaultInstance();
            return (realm.where(Movie.class).equalTo("feature", true).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Movie> getFavoriteMovies() {
        try {
            Realm realm = Realm.getDefaultInstance();
            return (realm.where(Movie.class).equalTo("favorite", true).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void saveAllTopList(List<Movie> movies) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(movies);
        realm.commitTransaction();

    }

    public static Movie getSingleMovie(int trackID) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Movie account = realm.where(Movie.class).equalTo("trackId", trackID).findFirst();
        realm.commitTransaction();
        return account;

    }

    public static void updateMovieMyListStatus(final Movie mov, final boolean favorite) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Movie movie = realm.where(Movie.class).equalTo("trackId", mov.trackId).findFirst();
        if (favorite)
            movie.favorite = false;
        else
            movie.favorite = true;
        realm.commitTransaction();
    }


}
