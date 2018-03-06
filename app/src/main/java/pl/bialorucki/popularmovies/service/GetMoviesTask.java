package pl.bialorucki.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import pl.bialorucki.popularmovies.BuildConfig;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.model.MoviesList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

public class GetMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
    @Override
    protected List<Movie> doInBackground(Void... voids) {
        // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                //      .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieRetrofitService moviesService = retrofit.create(MovieRetrofitService.class);
        try {
            MoviesList movies = moviesService.getMoviesByPopularity(BuildConfig.API_KEY).execute().body();
            Log.d("Movies", String.valueOf(movies.getMovies()));
            return movies.getMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
