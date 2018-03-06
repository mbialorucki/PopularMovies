package pl.bialorucki.popularmovies.ui.mainScreen;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.bialorucki.popularmovies.BuildConfig;
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.service.GetMoviesTask;
import pl.bialorucki.popularmovies.service.MovieRetrofitService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View{


    @BindView(R.id.mainGrid_rv)
    RecyclerView mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



    }

    private void configureRecycleView(){
        mainGrid.setLayoutManager(new GridLayoutManager(this,2));
        //mainGrid.setAdapter(new MoviesAdapter());
    }
}
