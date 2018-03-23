package pl.bialorucki.popularmovies.ui.detailScreen;

import pl.bialorucki.popularmovies.model.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Maciej Bialorucki on 22.03.18.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailsPresenterTest {

    @Mock
    private DetailScreenContract.View mockView;// = mock(DetailScreenContract.View.class);


    @Test
    public void should_show_movie_details_when_movie_is_not_null(){
        DetailScreenContract.Presenter presenter = new DetailsPresenter();
        presenter.attachView(mockView);

        Movie movie = new Movie();
        presenter.loadMovieDetails(movie);

        verify(mockView).showMovieDetails(movie);
    }

    @Test
    public void should_show_error_message_when_movie_is_null(){
        DetailScreenContract.Presenter presenter = new DetailsPresenter();
        presenter.attachView(mockView);

        presenter.loadMovieDetails(null);

        verify(mockView).showError();

    }
}