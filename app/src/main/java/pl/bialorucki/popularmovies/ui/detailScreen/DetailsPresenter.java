package pl.bialorucki.popularmovies.ui.detailScreen;

import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.base.BasePresenter;

/**
 * Created by Maciej Bialorucki on 16.03.18.
 */

public class DetailsPresenter extends BasePresenter<DetailScreenContract.View> implements DetailScreenContract.Presenter<DetailScreenContract.View> {
    @Override
    public void loadMovieDetails(Movie movie) {
        if (movie != null) {
            view.showMovieDetails(movie);
        } else {
            view.showError();
        }

    }
}
