package pl.bialorucki.popularmovies.service.tasks;

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

class GetPopularMoviesTask extends AsyncTask<Void, Void, List<Movie>> {

    private static final String TAG = "GetPopularMoviesTask";

    private MovieRetrofitService moviesService;

    public GetPopularMoviesTask(MovieRetrofitService moviesService) {
        this.moviesService = moviesService;
    }

    public void setMoviesService(MovieRetrofitService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {

        try {
            MoviesList movies = moviesService.getMoviesByPopularity(BuildConfig.API_KEY).execute().body();
            return movies.getMovies();
        } catch (IOException e) {
            Log.d(TAG,e.toString());
        }
        return Collections.emptyList();
    }
}
