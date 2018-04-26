package pl.bialorucki.popularmovies.service.retrofit;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.bialorucki.popularmovies.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */

public class RetrofitHelper {
    public static MovieRetrofitService createRetrofitMoviesClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl httpUrl = original.url();

                    HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter("api_key",BuildConfig.API_KEY).build();

                    Request.Builder requBuilder = original.newBuilder().url(newHttpUrl);
                    Request request = requBuilder.build();

                    return chain.proceed(request);
                 })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(MovieRetrofitService.class);
    }
}
