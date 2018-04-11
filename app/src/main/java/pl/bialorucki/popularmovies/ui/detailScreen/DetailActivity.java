package pl.bialorucki.popularmovies.ui.detailScreen;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.model.Trailer;
import pl.bialorucki.popularmovies.model.TrailerList;
import pl.bialorucki.popularmovies.utils.Utils;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.helper.ImageLoader;
import pl.bialorucki.popularmovies.ui.helper.PicassoImageLoader;
import timber.log.Timber;

public class DetailActivity extends AppCompatActivity implements DetailScreenContract.View{

    private static final String MOVIE_KEY = "movie";

    @BindView(R.id.header_iv)
    ImageView header;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description_tv)
    TextView description;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.cover_iv)
    ImageView coverImage;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.avg_rating)
    TextView avgRating;
    @BindView(R.id.original_language)
    TextView originalLanguage;
    @BindView(R.id.number_of_votes)
    TextView numberOfVotes;
    @BindView(R.id.trailers_list)
    LinearLayout trailersList;
    @BindView(R.id.reviews_list)
    LinearLayout reviewsList;

    private DetailScreenContract.Presenter<DetailScreenContract.View> presenter;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        presenter = new DetailsPresenter();
        presenter.attachView(this);

        imageLoader = new PicassoImageLoader();

        if (getIntent().getExtras() != null) {
            Movie movieToDisplay = getIntent().getExtras().getParcelable(MOVIE_KEY);
            presenter.loadMovieDetails(movieToDisplay);
            presenter.loadMovieTrailers(movieToDisplay.getId());
            presenter.loadMovieReviews(movieToDisplay.getId());
        }
    }

    @Override
    public void showMovieDetails(Movie movieToDisplay) {

        imageLoader.loadImage(this,Utils.BASE_PATH_BACKDROP + movieToDisplay.getBackdrop_path(),header);
        imageLoader.loadImage(this,Utils.BASE_PATH + movieToDisplay.getPoster_path(),coverImage);

        title.setText(movieToDisplay.getTitle());
        description.setText(movieToDisplay.getOverview());
        releaseDate.setText(movieToDisplay.getRelease_date());
        avgRating.setText(movieToDisplay.getVote_average());
        originalLanguage.setText(movieToDisplay.getOriginal_language().toUpperCase());
        numberOfVotes.setText(movieToDisplay.getVote_count());
        presenter.loadMovieTrailers(movieToDisplay.getId());
    }

    @Override
    public void showTrailers(TrailerList trailers) {
        int i = 1;
        for (Trailer trailer:
             trailers.getTrailers()) {
            TextView child = new TextView(this);
            child.setText(String.valueOf(i));
            trailersList.addView(child);
            i++;
        }
        Log.d("Trailer  s : ", String.valueOf(trailers.getTrailers().size()));
    }

    @Override
    public void showError() {
        Snackbar.make(collapsingToolbarLayout,R.string.movie_load_error,Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
