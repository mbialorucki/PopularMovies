package pl.bialorucki.popularmovies.ui.mainScreen;

import android.os.AsyncTask;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.service.retrofit.MovieRetrofitService;
import pl.bialorucki.popularmovies.service.tasks.MoviesService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */
public class MainPresenterTest {


    private MainScreenContract.View mockView = mock(MainActivity.class);
    private static MoviesService mockService = mock(MoviesService.class);

    @BeforeClass
    public static void init(){
        when(mockService.getHighestRatedMovies()).thenReturn(Collections.emptyList());
        when(mockService.getMostPopularMovies()).thenReturn(Collections.emptyList());
    }

    @Test
    public void should_call_view_methods(){
        MainPresenter presenter = new MainPresenter();
        presenter.attachView(mockView);
        presenter.setMoviesService(mockService);
        presenter.loadMostPopularMovies();

        verify(mockView).showLoadingIndicator();
        verify(mockView).showMovies(Collections.emptyList());
        verify(mockView).hideLoadingIndicator();
    }
}