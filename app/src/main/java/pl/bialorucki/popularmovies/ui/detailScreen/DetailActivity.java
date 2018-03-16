package pl.bialorucki.popularmovies.ui.detailScreen;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.Utils;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.helper.ImageLoader;
import pl.bialorucki.popularmovies.ui.helper.PicassoImageLoader;

public class DetailActivity extends AppCompatActivity implements DetailScreenContract.View{

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
    @BindView(R.id.relase_date)
    TextView releaseDate;
    @BindView(R.id.avg_rating)
    TextView avgRating;
    @BindView(R.id.original_language)
    TextView originalLanguage;
    @BindView(R.id.number_of_votes)
    TextView numberOfVotes;

    private Movie movieToDisplay;
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
            movieToDisplay = getIntent().getExtras().getParcelable("movie");
            presenter.loadMovieDetails(movieToDisplay);
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
    }

    @Override
    public void showError() {
        Snackbar.make(collapsingToolbarLayout,R.string.movie_load_error,Snackbar.LENGTH_INDEFINITE).show();
    }
}
