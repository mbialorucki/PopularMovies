package pl.bialorucki.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import pl.bialorucki.popularmovies.BuildConfig;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.model.MoviesList;
import pl.bialorucki.popularmovies.service.retrofit.MovieRetrofitService;
import pl.bialorucki.popularmovies.service.retrofit.RetrofitHelper;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

public class GetPopularMoviesTask extends AsyncTask<Void, Void, List<Movie>> {


    private MovieRetrofitService moviesService;

    public GetPopularMoviesTask(MovieRetrofitService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {

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
