package pl.bialorucki.popularmovies.ui.mainScreen;

import pl.bialorucki.popularmovies.service.tasks.MoviesService;
import pl.bialorucki.popularmovies.ui.base.BasePresenter;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

class MainPresenter extends BasePresenter<MainScreenContract.View> implements MainScreenContract.Presenter<MainScreenContract.View> {

    private MoviesService moviesService;

    public MainPresenter() {
        moviesService = new MoviesService();
    }

    public void setMoviesService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    public void loadMostPopularMovies() {
        view.showLoadingIndicator();
        view.showMovies(moviesService.getMostPopularMovies());
        view.hideLoadingIndicator();
    }

    @Override
    public void loadHighestRatedMovies() {
        view.showLoadingIndicator();
        view.showMovies(moviesService.getHighestRatedMovies());
        view.hideLoadingIndicator();
    }

}
