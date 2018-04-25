package pl.bialorucki.popularmovies.ui.detailScreen;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.bialorucki.popularmovies.BuildConfig;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.model.ReviewsList;
import pl.bialorucki.popularmovies.model.TrailerList;
import pl.bialorucki.popularmovies.service.retrofit.RetrofitHelper;
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

    @Override
    public void loadMovieTrailers(String id) {
        Observable<TrailerList> moviesByRating = RetrofitHelper.createRetrofitMoviesClient().getMovieTrailers(id,BuildConfig.API_KEY);
        moviesByRating.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                //.doOnComplete(() -> view.hideLoadingIndicator())
                .subscribe(trailersList -> view.showTrailers(trailersList));
    }
}
