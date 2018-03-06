package pl.bialorucki.popularmovies.ui.mainScreen;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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
    private MoviesAdapter gridAdapter;

    @BindView(R.id.mainProgressBar_pb)
    ProgressBar mainProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configureRecycleView();

        MainScreenContract.Presenter<MainScreenContract.View> presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.loadMovies();

    }

    private void configureRecycleView(){
        mainGrid.setLayoutManager(new GridLayoutManager(this,2));
        gridAdapter = new MoviesAdapter(Collections.EMPTY_LIST);
        mainGrid.setAdapter(gridAdapter);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        gridAdapter.setMovies(movies);
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingIndicator() {
        mainProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mainProgressBar.setVisibility(View.GONE);
    }
}
