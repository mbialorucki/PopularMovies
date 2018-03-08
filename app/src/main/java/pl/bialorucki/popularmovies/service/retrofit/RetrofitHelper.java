package pl.bialorucki.popularmovies.service.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */

public class RetrofitHelper {
    public static MovieRetrofitService createRetrofitMoviesClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MovieRetrofitService.class);
    }
}
