package pl.bialorucki.popularmovies.ui.mainScreen;

import java.util.List;

import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.base.MvpPresenter;
import pl.bialorucki.popularmovies.ui.base.MvpView;

/**
 * Created by Maciej Bialorucki on 02.03.18.
 */

interface MainScreenContract {

    interface View extends MvpView {

        void showMovies(List<Movie> movies);

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showNoNetworkConnectionInfo();
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {

        void loadMostPopularMovies();
        void loadHighestRatedMovies();
        void loadLastSelectedMovies(String sortingType);
        void checkInternetAccess(boolean internetAvailable);
    }
}
