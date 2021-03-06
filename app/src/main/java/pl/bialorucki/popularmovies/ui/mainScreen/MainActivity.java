package pl.bialorucki.popularmovies.ui.mainScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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
import pl.bialorucki.popularmovies.data.db.FavouriteMoviesDbHelper;
import pl.bialorucki.popularmovies.data.db.FavouriteMoviesRepository;
import pl.bialorucki.popularmovies.data.db.SQLiteRepository;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.detailScreen.DetailActivity;
import pl.bialorucki.popularmovies.utils.AndroidUtils;
import pl.bialorucki.popularmovies.utils.Utils;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    @BindView(R.id.mainGrid_rv)
    RecyclerView mainGrid;
    @BindView(R.id.mainProgressBar_pb)
    ProgressBar mainProgressBar;

    private MoviesAdapter gridAdapter;
    private MainScreenContract.Presenter<MainScreenContract.View> presenter;
    private String sortingStrategy;
    private AndroidUtils androidUtils;
    private Parcelable recycleViewOldState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sortingStrategy = Utils.MOST_POPULAR_STRATEGY;
        if (savedInstanceState != null) {
            sortingStrategy = savedInstanceState.getString(Utils.SORTING_STRATEGY, Utils.MOST_POPULAR_STRATEGY);
        }
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configureRecycleView();

        FavouriteMoviesRepository moviesRepository = new SQLiteRepository(new FavouriteMoviesDbHelper(this));
        presenter = new MainPresenter(moviesRepository);
        presenter.attachView(this);

        androidUtils = new AndroidUtils(this);
        boolean internetAvailable = androidUtils.isInternetAvailable();
        presenter.checkInternetAccess(internetAvailable);

        if (internetAvailable) {

            presenter.loadLastSelectedMovies(sortingStrategy);

            if (savedInstanceState != null) {
                recycleViewOldState = savedInstanceState.getParcelable(Utils.RECYCLE_VIEW_POSITION_KEY);
            }
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(Utils.SORTING_STRATEGY, sortingStrategy);
        outState.putParcelable(Utils.RECYCLE_VIEW_POSITION_KEY,mainGrid.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
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
                if (androidUtils.isInternetAvailable()) {
                    presenter.loadMostPopularMovies();
                    sortingStrategy = Utils.MOST_POPULAR_STRATEGY;
                }
                return true;
            case R.id.show_highest_rated:
                if (androidUtils.isInternetAvailable()) {
                    presenter.loadHighestRatedMovies();
                    sortingStrategy = Utils.HIGHEST_RATED_STRATEGY;
                }
                return true;
            case R.id.show_favourite:
                if (androidUtils.isInternetAvailable()) {
                    presenter.loadFavouriteMovies();
                    sortingStrategy = Utils.FAVOURITE_STRATEGY;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureRecycleView() {
        mainGrid.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
        gridAdapter = new MoviesAdapter(Collections.EMPTY_LIST, movie -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        });
        mainGrid.setAdapter(gridAdapter);
    }

    private static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 180;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 2)
            noOfColumns = 2;
        return noOfColumns;
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
        if(recycleViewOldState != null){
            mainGrid.getLayoutManager().onRestoreInstanceState(recycleViewOldState);
        }
    }

    @Override
    public void showNoNetworkConnectionInfo() {
        Snackbar.make(mainGrid, R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE).show();
        hideLoadingIndicator();
    }
}
