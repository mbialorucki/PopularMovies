package pl.bialorucki.popularmovies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pl.bialorucki.popularmovies.data.contentProvider.PopularMoviesContentProvider;
import pl.bialorucki.popularmovies.data.db.FavouriteMoviesContract;
import pl.bialorucki.popularmovies.model.Movie;

import static pl.bialorucki.popularmovies.data.db.FavouriteMoviesContract.FavoriteEntry.*;

/**
 * Created by Maciej Bialorucki on 18.04.18.
 */
public class ContentProviderClient {

    private final Context context;

    public ContentProviderClient(Context context){
        this.context = context;
    }

    public boolean isFavourite(Movie movie){
        Cursor query = context.getContentResolver().query(PopularMoviesContentProvider.CONTENT_URI, null, FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ID + movie.getId(), null, null);
        return query.getCount() != 0;

    }
    public void makeMovieFavourite(Movie movie){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID,movie.getId());
        values.put(COLUMN_NAME_TITLE,movie.getTitle());
        values.put(COLUMN_NAME_POSTER_PATH,movie.getPoster_path());
        values.put(COLUMN_NAME_BACKDROP_PATH,movie.getBackdrop_path());
        values.put(COLUMN_NAME_DESCRIPTION,movie.getOverview());
        values.put(COLUMN_NAME_RELEASE_DATE,movie.getRelease_date());
        values.put(COLUMN_NAME_AVG_RATING,movie.getVote_average());
        values.put(COLUMN_NAME_ORIGINAL_LANGUAGE,movie.getOriginal_language());
        values.put(COLUMN_NAME_NUMBER_OF_VOTES,movie.getVote_count());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_FAVOURITE, movie.isFavourite() ? 1 : 0);

        context.getContentResolver().insert(PopularMoviesContentProvider.CONTENT_URI,values);
    }

    public void unmadeMovieFavourite(Movie movie){
        context.getContentResolver().delete(PopularMoviesContentProvider.CONTENT_URI,FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ID+"="+movie.getId(),null);
    }

    public List<Movie> getAll(){
        List<Movie> result = new ArrayList<>();
        Cursor query = context.getContentResolver().query(PopularMoviesContentProvider.CONTENT_URI, null, null, null, null);
        while (query.moveToNext()) {
            int movieIdIndex = query.getColumnIndex(COLUMN_NAME_ID);
            int titleIndex = query.getColumnIndex(COLUMN_NAME_TITLE);
            int posterIndex = query.getColumnIndex(COLUMN_NAME_POSTER_PATH);
            int backdropIndex = query.getColumnIndex(COLUMN_NAME_BACKDROP_PATH);
            int descriptionIndex = query.getColumnIndex(COLUMN_NAME_DESCRIPTION);
            int releaseDateIndex = query.getColumnIndex(COLUMN_NAME_RELEASE_DATE);
            int avgRatingIndex = query.getColumnIndex(COLUMN_NAME_AVG_RATING);
            int oryginalLanguageIndex = query.getColumnIndex(COLUMN_NAME_ORIGINAL_LANGUAGE);
            int numberOfVotesIndex = query.getColumnIndex(COLUMN_NAME_NUMBER_OF_VOTES);
            int favoriteIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_FAVOURITE);

            String movieId = query.getString(movieIdIndex);
            String title = query.getString(titleIndex);
            String posterPath = query.getString(posterIndex);
            String backdropPath = query.getString(backdropIndex);
            String description = query.getString(descriptionIndex);
            String releaseDate = query.getString(releaseDateIndex);
            String avgRating = query.getString(avgRatingIndex);
            String originalLanguage = query.getString(oryginalLanguageIndex);
            String numberOfVotes = query.getString(numberOfVotesIndex);
            int favorite = query.getInt(favoriteIndex);

            Movie movie = new Movie();
            movie.setId(movieId);
            movie.setTitle(title);
            movie.setPoster_path(posterPath);
            movie.setBackdrop_path(backdropPath);
            movie.setOverview(description);
            movie.setRelease_date(releaseDate);
            movie.setVote_average(avgRating);
            movie.setOriginal_language(originalLanguage);
            movie.setVote_count(numberOfVotes);
            movie.setFavourite(favorite == 1);

            result.add(movie);
        }

        return result;
    }
}
