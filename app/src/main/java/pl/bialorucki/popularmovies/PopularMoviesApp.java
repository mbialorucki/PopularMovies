package pl.bialorucki.popularmovies;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Maciej Bialorucki on 02.03.18.
 */

public class PopularMoviesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Stetho.initializeWithDefaults(this);
    }
}
