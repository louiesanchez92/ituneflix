package com.appetiser.ituneflix.helper;

import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.api.models.movies.SearchMovies;

public class ModelCopy {

    /**
     * We get a fresh copy of movie
     * from search movie DB
     * @param movies
     * @return
     */
    public static Movie getMovieCopy(SearchMovies movies) {
        Movie movie = new Movie();
        movie.trackId = movies.trackId;
        movie.wrapperType = movies.wrapperType;
        movie.kind = movies.kind;
        movie.artistName = movies.artistName;
        movie.trackName = movies.trackName;
        movie.trackCensoredName = movies.trackCensoredName;
        movie.trackViewUrl = movies.trackViewUrl;
        movie.previewUrl = movies.previewUrl;
        movie.artworkUrl30 = movies.artworkUrl30;
        movie.artworkUrl60 = movies.artworkUrl60;
        movie.artworkUrl100 = movies.artworkUrl100;
        movie.collectionPrice = movies.collectionPrice;
        movie.trackPrice = movies.trackPrice;
        movie.trackHdRentalPrice = movies.trackHdRentalPrice;
        movie.trackRentalPrice = movies.trackRentalPrice;
        movie.collectionHdPrice = movies.collectionHdPrice;
        movie.collectionPrice = movies.collectionPrice;
        movie.releaseDate = movies.releaseDate;
        movie.collectionExplicitness = movies.collectionExplicitness;
        movie.trackTimeMillis = movies.trackTimeMillis;
        movie.country = movies.country;
        movie.currency = movies.currency;
        movie.primaryGenreName = movies.primaryGenreName;
        movie.contentAdvisoryRating = movies.contentAdvisoryRating;
        movie.shortDescription = movies.shortDescription;
        movie.longDescription = movies.longDescription;
        movie.hasITunesExtras = movies.hasITunesExtras;
        movie.favorite = movies.favorite;
        movie.feature = movies.feature;
        return movie;

    }

}
