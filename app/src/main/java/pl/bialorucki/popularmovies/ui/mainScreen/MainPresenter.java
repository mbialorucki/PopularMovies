package pl.bialorucki.popularmovies.ui.mainScreen;

import java.util.List;
import java.util.concurrent.ExecutionException;

import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.service.GetMoviesTask;
import pl.bialorucki.popularmovies.ui.base.BasePresenter;
import pl.bialorucki.popularmovies.ui.base.MvpPresenter;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

class MainPresenter extends BasePresenter<MainScreenContract.View> implements MainScreenContract.Presenter<MainScreenContract.View> {


    @Override
    public void loadMovies() {
        GetMoviesTask task = new GetMoviesTask();
        try {
            view.showLoadingIndicator();
            List<Movie> movies = task.execute().get();
            view.hideLoadingIndicator();
            view.showMovies(movies);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
