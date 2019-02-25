package com.appetiser.ituneflix.pages.home.top;

import android.support.annotation.NonNull;

import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.api.models.movies.MovieResults;
import com.appetiser.ituneflix.api.rxfit.ApiClient;
import com.appetiser.ituneflix.api.rxfit.ApiService;
import com.appetiser.ituneflix.db.MoviesDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TopListFragmentPresenter implements TopListFragmentPresenterInterface {


    TopListViewInterface tvi;

    public TopListFragmentPresenter(TopListViewInterface tvi) {
        this.tvi = tvi;
    }


    public Observable<MovieResults> getObservable() {

        Map<String, String> params = new HashMap<>();
        params.put("limit", "25");
        params.put("term", "star");
        params.put("country", "au");

        return ApiService.getRetrofit().create(ApiClient.class)
                .getSearch(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResults> getObserver() {
        return new DisposableObserver<MovieResults>() {

            @Override
            public void onNext(@NonNull MovieResults responseBody) {
                List<Movie> movies = setMovieListAsFeaturedList(responseBody.results);
                MoviesDB.saveAllTopList(movies);
                tvi.displayMovies(movies);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                tvi.displayError();
            }

            @Override
            public void onComplete() {
            }
        };
    }


    @Override
    public void getListOfMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.addAll(MoviesDB.getTopMoviesList());
        if (movies.isEmpty()) {
            getObservable().subscribeWith(getObserver());
        } else {
            tvi.displayMovies(movies);
        }
    }

    public List<Movie> setMovieListAsFeaturedList(List<Movie> movies) {

        int size = movies.size();
        for (int i = 0; i < size; i++) {
            movies.get(i).feature = true;
        }

        return movies;

    }
}
