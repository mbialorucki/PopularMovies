package pl.bialorucki.popularmovies.service.tasks;

import android.os.AsyncTask;

import java.util.List;

import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.service.retrofit.RetrofitHelper;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */

class MoviesTaskFactory {

    public static AsyncTask<Void, Void, List<Movie>> getTask(String key) {

        switch (key) {
            case SortingStrategies.HIGHEST_RATED:
                return new GetHighestRatedMoviesTask(RetrofitHelper.createRetrofitMoviesClient());
            case SortingStrategies.MOST_POPULAR:
                return new GetPopularMoviesTask(RetrofitHelper.createRetrofitMoviesClient());
            default:
                new GetPopularMoviesTask(RetrofitHelper.createRetrofitMoviesClient());
        }
        return new GetPopularMoviesTask(RetrofitHelper.createRetrofitMoviesClient());
    }
}


