package pl.bialorucki.popularmovies.service.tasks;

import android.os.AsyncTask;

import org.junit.Test;

import java.util.List;

import pl.bialorucki.popularmovies.model.Movie;

import static org.junit.Assert.*;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */
public class MoviesTaskFactoryTest {

    @Test
    public void should_return_most_popular_movies_task(){
        AsyncTask<Void, Void, List<Movie>> task = MoviesTaskFactory.getTask(SortingStrategies.MOST_POPULAR);
        assertTrue(task instanceof GetPopularMoviesTask);
    }

    @Test
    public void should_return_highest_rated_movies_task(){
        AsyncTask<Void, Void, List<Movie>> task = MoviesTaskFactory.getTask(SortingStrategies.HIGHEST_RATED);
        assertTrue(task instanceof GetHighestRatedMoviesTask);
    }

    @Test
    public void should_return_most_popular_movies_task_for_empty_string(){
        AsyncTask<Void, Void, List<Movie>> task = MoviesTaskFactory.getTask("");
        assertTrue(task instanceof GetPopularMoviesTask);
    }
}