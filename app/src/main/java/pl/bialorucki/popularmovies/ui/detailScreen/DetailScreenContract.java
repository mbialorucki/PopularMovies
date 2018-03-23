package pl.bialorucki.popularmovies.ui.detailScreen;

import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.base.MvpPresenter;
import pl.bialorucki.popularmovies.ui.base.MvpView;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

public interface DetailScreenContract {

    interface View extends MvpView {
        void showMovieDetails(Movie movie);
        void showError();
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {

        void loadMovieDetails(Movie movie);
    }
}
