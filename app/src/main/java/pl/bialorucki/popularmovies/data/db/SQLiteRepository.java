package pl.bialorucki.popularmovies.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pl.bialorucki.popularmovies.model.Movie;

/**
 * Created by Maciej Bialorucki on 18.04.18.
 */
public class SQLiteRepository implements FavouriteMoviesRepository {

    private final FavouriteMoviesDbHelper dbHelper;

    public SQLiteRepository(FavouriteMoviesDbHelper helper) {
        this.dbHelper = helper;
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> result = new ArrayList<>();

        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(FavouriteMoviesContract.FavoriteEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ID);
        while (query.moveToNext()) {
            int movieIdIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ID);
            int titleIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_TITLE);
            int posterIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH);
            int backdropIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_BACKDROP_PATH);
            int descriptionIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_DESCRIPTION);
            int releaseDateIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_RELEASE_DATE);
            int avgRatingIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_AVG_RATING);
            int oryginalLanguageIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ORIGINAL_LANGUAGE);
            int numberOfVotesIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_NUMBER_OF_VOTES);
            int favoriteIndex = query.getColumnIndex(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_FAVOURITE);

            String movieId = query.getString(movieIdIndex);
            String title = query.getString(titleIndex);
            String posterPath = query.getString(posterIndex);
            String backdropPath = query.getString(backdropIndex);
            String description = query.getString(descriptionIndex);
            String releaseDate = query.getString(releaseDateIndex);
            String avgRating = query.getString(avgRatingIndex);
            String oryginalLanguage = query.getString(oryginalLanguageIndex);
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
            movie.setOriginal_language(oryginalLanguage);
            movie.setVote_count(numberOfVotes);
            movie.setFavourite(favorite == 1);

            result.add(movie);
        }

        return result;

    }

    @Override
    public void remove(Movie movie) {
        remove(movie.getId());
    }

    @Override
    public void remove(String movieId) {
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        writableDatabase.delete(FavouriteMoviesContract.FavoriteEntry.TABLE_NAME, FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ID + "=" + movieId, null);
    }

    @Override
    public void add(Movie movie) {
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ID, movie.getId());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_TITLE, movie.getTitle());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH, movie.getPoster_path());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_BACKDROP_PATH, movie.getBackdrop_path());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_DESCRIPTION, movie.getOverview());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_RELEASE_DATE, movie.getRelease_date());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_AVG_RATING, movie.getVote_average());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_ORIGINAL_LANGUAGE, movie.getOriginal_language());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_NUMBER_OF_VOTES, movie.getVote_count());
        values.put(FavouriteMoviesContract.FavoriteEntry.COLUMN_NAME_FAVOURITE, movie.isFavourite() ? 1 : 0);

        writableDatabase.insert(FavouriteMoviesContract.FavoriteEntry.TABLE_NAME, null, values);
    }
}
