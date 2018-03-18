package pl.bialorucki.popularmovies.ui.mainScreen;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.bialorucki.popularmovies.BuildConfig;
import pl.bialorucki.popularmovies.Utils;
import pl.bialorucki.popularmovies.model.MoviesList;
import pl.bialorucki.popularmovies.service.retrofit.RetrofitHelper;
import pl.bialorucki.popularmovies.ui.base.BasePresenter;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

class MainPresenter extends BasePresenter<MainScreenContract.View> implements MainScreenContract.Presenter<MainScreenContract.View> {

    @Override
    public void loadMostPopularMovies() {
        Observable<MoviesList> movies = RetrofitHelper.createRetrofitMoviesClient().getMoviesByPopularity(BuildConfig.API_KEY);
        loadMovies(movies);
    }

    @Override
    public void loadHighestRatedMovies() {
        Observable<MoviesList> moviesByRating = RetrofitHelper.createRetrofitMoviesClient().getMoviesByRating(BuildConfig.API_KEY);
        loadMovies(moviesByRating);
    }

    @Override
    public void loadLastSelectedMovies(String sortingType) {

        switch (sortingType){
            case Utils.MOST_POPULAR_STRATEGY:
                loadMostPopularMovies();
                break;
            case Utils.HIGHEST_RATED_STRATEGY:
                loadHighestRatedMovies();
                break;
        }

    }

    private void loadMovies(Observable<MoviesList> movies) {
        view.showLoadingIndicator();

        movies.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> view.hideLoadingIndicator())
                .subscribe(moviesList -> view.showMovies(moviesList.getMovies()));
    }

}
