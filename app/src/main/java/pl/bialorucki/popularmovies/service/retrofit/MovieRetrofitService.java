package pl.bialorucki.popularmovies.service.retrofit;

import io.reactivex.Observable;
import pl.bialorucki.popularmovies.model.*;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

public interface MovieRetrofitService {

    @GET("movie/{sort_by}")
    Observable<MoviesList> getMovies(@Path("sort_by") String sortBy);

    @GET("movie/{id}/videos")
    Observable<TrailerList> getMovieTrailers(@Path("id") String movieId);
    @GET("movie/{id}/reviews")
    Observable<ReviewsList> getMovieReviews(@Path("id") String movieId);
}
