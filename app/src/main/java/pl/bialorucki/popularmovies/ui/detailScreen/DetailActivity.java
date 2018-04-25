package pl.bialorucki.popularmovies.ui.detailScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.data.ContentProviderClient;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.model.ReviewsList;
import pl.bialorucki.popularmovies.model.TrailerList;
import pl.bialorucki.popularmovies.ui.helper.ImageLoader;
import pl.bialorucki.popularmovies.ui.helper.PicassoImageLoader;
import pl.bialorucki.popularmovies.utils.Utils;

public class DetailActivity extends AppCompatActivity implements DetailScreenContract.View {

    private static final String TAG = "DetailActivity";
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
    @BindView(R.id.reviews_list)
    RecyclerView reviewsList;
    @BindView(R.id.trailers_list)
    RecyclerView trailersList;
    @BindView(R.id.empty_view)
    TextView emptyTrailersList;
    @BindView(R.id.empty_reviews)
    TextView emptyReviewsList;
    @BindView(R.id.fav_button)
    FloatingActionButton favouriteButton;

    private DetailScreenContract.Presenter<DetailScreenContract.View> presenter;
    private ImageLoader imageLoader;
    private ReviewsAdapter reviewsAdapter;
    private TrailersAdapter trailersAdapter;
    private Movie movieToDisplay;
    private ContentProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        client = new ContentProviderClient(this);
        presenter = new DetailsPresenter();
        presenter.attachView(this);

        imageLoader = new PicassoImageLoader();

        if (getIntent().getExtras() != null) {
            configureRecycleView();
            configureTrailersRecycleView();
            movieToDisplay = getIntent().getExtras().getParcelable(MOVIE_KEY);
            checkIfMovieIsFavourite();
            presenter.loadMovieDetails(movieToDisplay);
            presenter.loadMovieTrailers(movieToDisplay.getId());
            presenter.loadMovieReviews(movieToDisplay.getId());

        }
    }

    private void checkIfMovieIsFavourite() {
        if(client.isFavourite(movieToDisplay)){
            favouriteButton.setImageDrawable(getDrawable(android.R.drawable.star_big_on));
        }else{
            favouriteButton.setImageDrawable(getDrawable(android.R.drawable.star_big_off));

        }
    }


    @OnClick(R.id.fav_button)
    void onFavouriteButtonClick(){
        presenter.changeMovieFavouriteState(movieToDisplay);
    }
    @Override
    public void showMovieDetails(Movie movieToDisplay) {

        imageLoader.loadImage(this, Utils.BASE_PATH_BACKDROP + movieToDisplay.getBackdrop_path(), header);
        imageLoader.loadImage(this, Utils.BASE_PATH + movieToDisplay.getPoster_path(), coverImage);

        title.setText(movieToDisplay.getTitle());
        description.setText(movieToDisplay.getOverview());
        releaseDate.setText(movieToDisplay.getRelease_date());
        avgRating.setText(movieToDisplay.getVote_average());
        originalLanguage.setText(movieToDisplay.getOriginal_language().toUpperCase());
        numberOfVotes.setText(movieToDisplay.getVote_count());
        presenter.loadMovieTrailers(movieToDisplay.getId());
    }



    @Override
    public void showError() {
        Snackbar.make(collapsingToolbarLayout, R.string.movie_load_error, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showTrailers(TrailerList trailers) {
        trailersList.setVisibility(View.VISIBLE);
        trailersAdapter.setTrailers(trailers);
        trailersAdapter.notifyDataSetChanged();
    }

    @Override
    public void showReviews(ReviewsList reviews) {
        reviewsList.setVisibility(View.VISIBLE);
        reviewsAdapter.setReviews(reviews.getReviews());
        reviewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoTrailers() {
        emptyTrailersList.setVisibility(View.VISIBLE);
        trailersList.setVisibility(View.GONE);
    }

    @Override
    public void showNoReviews() {
        emptyReviewsList.setVisibility(View.VISIBLE);
        reviewsList.setVisibility(View.GONE);
    }

    @Override
    public void changeFavouriteButtonOff() {
        movieToDisplay.setFavourite(false);
        client.unmadeMovieFavourite(movieToDisplay);
        favouriteButton.setImageDrawable(getDrawable(android.R.drawable.star_big_off));
    }

    @Override
    public void changeFavouriteButtonOn() {
        movieToDisplay.setFavourite(true);
        client.makeMovieFavourite(movieToDisplay);
        favouriteButton.setImageDrawable(getDrawable(android.R.drawable.star_big_on));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void configureRecycleView() {
        reviewsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        reviewsAdapter = new ReviewsAdapter(Collections.EMPTY_LIST, review -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(review.getUrl()));
            startActivity(i);
        });
        reviewsList.setAdapter(reviewsAdapter);
    }

    private void configureTrailersRecycleView() {
        trailersList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trailersAdapter = new TrailersAdapter(Collections.emptyList(),trailer -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(Utils.YOUTUBE_URL+trailer.getKey()));
            startActivity(i);
        });
        trailersList.setAdapter(trailersAdapter);
    }
}
