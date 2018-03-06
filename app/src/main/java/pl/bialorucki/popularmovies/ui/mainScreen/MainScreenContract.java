package pl.bialorucki.popularmovies.ui.mainScreen;

import java.util.List;

import pl.bialorucki.popularmovies.ui.base.MvpPresenter;
import pl.bialorucki.popularmovies.ui.base.MvpView;
import pl.bialorucki.popularmovies.model.*;

/**
 * Created by Maciej Bialorucki on 02.03.18.
 */

public interface MainScreenContract {

    interface View extends MvpView {

        void showMovies(List<Movie> movies);
        void showLoadingIndicator();
        void hideLoadingIndicator();
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V>{
        void loadMovies();
    }
}
