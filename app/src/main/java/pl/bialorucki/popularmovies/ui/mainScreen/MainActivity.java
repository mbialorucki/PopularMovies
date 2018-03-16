package pl.bialorucki.popularmovies.ui.mainScreen;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.model.Movie;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {


    private static final String SORTING_STRATEGY = "sorting_strategy";
    private static final String HIGHEST_RATED_STRATEGY = "highest_rated";
    public static final String MOST_POPULAR = "most_popular";

    @BindView(R.id.mainGrid_rv)
    RecyclerView mainGrid;
    @BindView(R.id.mainProgressBar_pb)
    ProgressBar mainProgressBar;

    private MoviesAdapter gridAdapter;
    private MainScreenContract.Presenter<MainScreenContract.View> presenter;
    private String sortingStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sortingStrategy = MOST_POPULAR;
        if(savedInstanceState != null){
           sortingStrategy = savedInstanceState.getString(SORTING_STRATEGY, MOST_POPULAR);
        }
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configureRecycleView();

        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.loadMostPopularMovies();


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(SORTING_STRATEGY,sortingStrategy);
        }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String string = savedInstanceState.getString(SORTING_STRATEGY, MOST_POPULAR);
        if(string.equals(MOST_POPULAR)) {
            presenter.loadMostPopularMovies();
        }else{
            presenter.loadHighestRatedMovies();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_most_popular:
                presenter.loadMostPopularMovies();
                sortingStrategy = MOST_POPULAR;
                return true;
            case R.id.show_highest_rated:
                presenter.loadHighestRatedMovies();
                sortingStrategy = HIGHEST_RATED_STRATEGY;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureRecycleView() {
        mainGrid.setLayoutManager(new GridLayoutManager(this, 2));
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
