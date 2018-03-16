package pl.bialorucki.popularmovies.ui.mainScreen;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.bialorucki.popularmovies.BuildConfig;
import pl.bialorucki.popularmovies.model.MoviesList;
import pl.bialorucki.popularmovies.service.retrofit.RetrofitHelper;
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
        Observable<MoviesList> moviesByPopularity = RetrofitHelper.createRetrofitMoviesClient().getMoviesByPopularity(BuildConfig.API_KEY);

        moviesByPopularity.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> view.hideLoadingIndicator())
                .subscribe(moviesList -> view.showMovies(moviesList.getMovies()));
    }

    @Override
    public void loadHighestRatedMovies() {
        view.showLoadingIndicator();
        Observable<MoviesList> moviesByRating = RetrofitHelper.createRetrofitMoviesClient().getMoviesByRating(BuildConfig.API_KEY);

        moviesByRating.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> view.hideLoadingIndicator())
                .subscribe(moviesList -> view.showMovies(moviesList.getMovies()));
    }

}
