package com.appetiser.ituneflix.pages.home.search;

import android.support.annotation.NonNull;
import android.util.Log;

import com.appetiser.ituneflix.AppSessions;
import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.api.models.movies.MovieResults;
import com.appetiser.ituneflix.api.rxfit.ApiClient;
import com.appetiser.ituneflix.api.rxfit.ApiService;
import com.appetiser.ituneflix.db.MoviesDB;
import com.appetiser.ituneflix.helper.TimeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchListActivityPresenter implements SearchListActivityPresenterInterface {


    SearchListViewInterface tvi;

    public SearchListActivityPresenter(SearchListViewInterface tvi) {
        this.tvi = tvi;
    }


    public Observable<MovieResults> getObservable(String searchText) {

        /**
         * List of Params
         * term - search text by the user
         * limit - limit result in every request
         * media - movie list only
         */
        Map<String, String> params = new HashMap<>();
        params.put("limit", "25");
        params.put("term", searchText);
        params.put("media", "movie");

        /**
         * Call for API request
         */
        return ApiService.getRetrofit().create(ApiClient.class)
                .getTopMovies(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResults> getObserver() {
        return new DisposableObserver<MovieResults>() {

            @Override
            public void onNext(@NonNull MovieResults responseBody) {
                /**
                 * Use the callback to display the movies
                 */
                List<Movie> movies = responseBody.results;

                /**
                 * Everytime there is a new list of search movies
                 * This movies will overwrite the last search movies
                 * and this list will be shown once the app is closed
                 * and reopen by the user
                 */
                if (!movies.isEmpty()) {
                    /**
                     * We save the current date
                     * where we save the list of movies
                     * This date will be displayed in the header part of the list
                     */
                    AppSessions.saveLastSearchDate(TimeHelper.getCurrent());

                    /**
                     * Here below is the saving of search list of movies
                     * happened
                     */
                    MoviesDB.saveAllTopList(movies);
                    /**
                     * Display list of movies
                     */
                    tvi.displayMovies(MoviesDB.getTopMoviesList());
                } else {
                    /**
                     * Put an empty list alert
                     */
                    tvi.displayError();
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                /**
                 * If there is an error happening
                 * throw an error callback
                 */
                tvi.displayError();
            }

            @Override
            public void onComplete() {

            }
        };
    }


    @Override
    public void getListOfMovies(String search) {
        getObservable(search).subscribeWith(getObserver());
    }

    @Override
    public void getListOfMoviesFromDB(String search) {
        List<Movie> movies = new ArrayList<>();
        if (search.isEmpty())
            movies.addAll(MoviesDB.getTopMoviesList());
        else
            movies.addAll(MoviesDB.getTopMoviesListWithFilter(search));
        tvi.displayMovies(movies);

    }

}
