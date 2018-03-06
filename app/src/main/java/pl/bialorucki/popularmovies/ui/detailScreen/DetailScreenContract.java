package pl.bialorucki.popularmovies.ui.detailScreen;

import java.util.List;

import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.base.MvpPresenter;
import pl.bialorucki.popularmovies.ui.base.MvpView;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

public class DetailScreenContract {

    interface View extends MvpView {
        void showMovieDetails(Movie movie);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
    }
}