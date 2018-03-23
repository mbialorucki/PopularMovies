package pl.bialorucki.popularmovies.service.retrofit;

import io.reactivex.Observable;
import pl.bialorucki.popularmovies.model.*;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

public interface MovieRetrofitService {

    @GET("movie/popular")
    Observable<MoviesList> getMoviesByPopularity(@Query("api_key") String apiKey);
    @GET("movie/top_rated")
    Observable<MoviesList> getMoviesByRating(@Query("api_key") String apiKey);
}
