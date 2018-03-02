package pl.bialorucki.popularmovies.ui.mainScreen;

import pl.bialorucki.popularmovies.ui.base.MvpPresenter;
import pl.bialorucki.popularmovies.ui.base.MvpView;

/**
 * Created by Maciej Bialorucki on 02.03.18.
 */

public interface MainScreenContract {

    interface View extends MvpView {

    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V>{

    }
}
