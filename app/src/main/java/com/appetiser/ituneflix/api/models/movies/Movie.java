package com.appetiser.ituneflix.api.models.movies;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Movie extends RealmObject {

    @PrimaryKey
    public int trackId;
    public String wrapperType;
    public String kind;
    public String artistName;
    public String trackName;
    public String trackCensoredName;
    public String trackViewUrl;
    public String previewUrl;
    public String artworkUrl30;
    public String artworkUrl60;
    public String artworkUrl100;
    public double collectionPrice;
    public double trackPrice;
    public double trackRentalPrice;
    public double collectionHdPrice;
    public double trackHdPrice;
    public double trackHdRentalPrice;
    public String releaseDate;
    public String collectionExplicitness;
    public int trackTimeMillis;
    public String country;
    public String currency;
    public String primaryGenreName;
    public String contentAdvisoryRating;
    public String shortDescription;
    public String longDescription;
    public boolean hasITunesExtras;
    public boolean favorite;
    public boolean feature;


}
