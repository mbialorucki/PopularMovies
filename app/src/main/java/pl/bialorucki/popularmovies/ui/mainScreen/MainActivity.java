package pl.bialorucki.popularmovies.ui.mainScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.bialorucki.popularmovies.BuildConfig;
import pl.bialorucki.popularmovies.R;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
