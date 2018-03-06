package pl.bialorucki.popularmovies.ui.base;

/**
 * Created by Maciej Bialorucki on 02.03.18.
 */

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    public V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }
}
