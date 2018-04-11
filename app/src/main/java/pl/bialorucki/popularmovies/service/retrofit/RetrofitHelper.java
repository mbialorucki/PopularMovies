package pl.bialorucki.popularmovies.service.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.bialorucki.popularmovies.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */

public class RetrofitHelper {
    public static MovieRetrofitService createRetrofitMoviesClient() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            okHttpClient.interceptors().add(interceptor);
//        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
             //   .client(okHttpClient)
                .build();

        return retrofit.create(MovieRetrofitService.class);
    }
}
