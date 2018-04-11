package pl.bialorucki.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maciej Bialorucki on 05.04.18.
 */
public class TrailerList {

    @SerializedName("results")
    private List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
