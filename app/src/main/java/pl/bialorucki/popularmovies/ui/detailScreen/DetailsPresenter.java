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
        Observable<TrailerList> trailers = RetrofitHelper.createRetrofitMoviesClient().getMovieTrailers(id);
        trailers.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trailersList -> {
                    if (trailersList.getTrailers().size() == 0) {
                        view.showNoTrailers();
                    } else {
                        view.showTrailers(trailersList);
                    }
                });
    }

    @Override
    public void loadMovieReviews(String id) {
        Observable<ReviewsList> reviews = RetrofitHelper.createRetrofitMoviesClient().getMovieReviews(id);
        reviews.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewsList -> {
                    if (reviewsList.getReviews().size() == 0) {
                        view.showNoReviews();
                    } else {
                        view.showReviews(reviewsList);
                    }
                });
    }

    @Override
    public void changeMovieFavouriteState(Movie movieToDisplay) {
        if (movieToDisplay == null)
            return;

        if (movieToDisplay.isFavourite()) {
            view.changeFavouriteButtonOff();
        } else {
            view.changeFavouriteButtonOn();
        }
    }
}
