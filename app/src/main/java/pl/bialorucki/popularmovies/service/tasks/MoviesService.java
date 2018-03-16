package pl.bialorucki.popularmovies.service.tasks;


import android.os.AsyncTask;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.model.MoviesList;
import pl.bialorucki.popularmovies.service.retrofit.MovieRetrofitService;
import pl.bialorucki.popularmovies.service.retrofit.RetrofitHelper;
import pl.bialorucki.popularmovies.ui.mainScreen.MainScreenContract;


/**
 * Created by Maciej Bialorucki on 08.03.18.
 */

public class MoviesService {

    public List<Movie> getHighestRatedMovies() {
        return getMovies(SortingStrategies.HIGHEST_RATED);
    }

    public List<Movie> getMostPopularMovies() {
        return getMovies(SortingStrategies.MOST_POPULAR);
    }

    private List<Movie> getMovies(String sortingKey) {


        try {
            AsyncTask<Void, Void, List<Movie>> task = MoviesTaskFactory.getTask(sortingKey);
            return task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
