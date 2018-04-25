package pl.bialorucki.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maciej Bialorucki on 11.04.18.
 */
public class ReviewsList {

    @SerializedName("results")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }
}
