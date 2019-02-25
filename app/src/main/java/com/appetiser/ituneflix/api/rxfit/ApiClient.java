package com.appetiser.ituneflix.api.rxfit;

import com.appetiser.ituneflix.api.models.movies.MovieResults;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiClient {

    @GET("/search")
    Observable<MovieResults> getTopMovies(@QueryMap Map<String, String> params);

}
