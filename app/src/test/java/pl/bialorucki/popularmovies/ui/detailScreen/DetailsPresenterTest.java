package pl.bialorucki.popularmovies.ui.detailScreen;

import org.junit.Test;

import pl.bialorucki.popularmovies.model.Movie;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Maciej Bialorucki on 16.03.18.
 */
public class DetailsPresenterTest {

    private DetailScreenContract.View mockView = mock(DetailActivity.class);
    @Test
    public void should_show_movie_details(){
        DetailsPresenter presenter = new DetailsPresenter();
        presenter.attachView(mockView);
        presenter.loadMovieDetails(new Movie());

        verify(mockView).showMovieDetails(new Movie());
    }

    @Test
    public void should_show_error_message_for_null(){
        DetailsPresenter presenter = new DetailsPresenter();
        presenter.attachView(mockView);
        presenter.loadMovieDetails(null);
        verify(mockView).showError();
    }
}