package pl.bialorucki.popularmovies.ui.base;

/**
 * Created by Maciej Bialorucki on 02.03.18.
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);
    void detachView();
    boolean isViewAttached();

}
