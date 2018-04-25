package pl.bialorucki.popularmovies.data.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.bialorucki.popularmovies.data.db.FavouriteMoviesContract;
import pl.bialorucki.popularmovies.data.db.FavouriteMoviesDbHelper;
import pl.bialorucki.popularmovies.data.db.FavouriteMoviesRepository;
import pl.bialorucki.popularmovies.data.db.SQLiteRepository;

import static pl.bialorucki.popularmovies.data.db.FavouriteMoviesContract.FavoriteEntry.*;

/**
 * Created by Maciej Bialorucki on 11.04.18.
 */
public class PopularMoviesContentProvider extends ContentProvider {

    public static final String AUTHORITY = "pl.bialorucki.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MOVIES = "favouriteMovies";
    public static final int MOVIES = 100;
    public static final int MOVIE_WITH_ID = 101;

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();


    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(AUTHORITY,PATH_MOVIES,MOVIES);
        matcher.addURI(AUTHORITY,PATH_MOVIES+"/#",MOVIE_WITH_ID);
    }

    private FavouriteMoviesRepository repository;
    private FavouriteMoviesDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new FavouriteMoviesDbHelper(getContext());
        repository = new SQLiteRepository(dbHelper);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (matcher.match(uri)) {
            case MOVIES:
                return dbHelper.getReadableDatabase().query(TABLE_NAME,null,selection,selectionArgs,null,null,sortOrder);
            case MOVIE_WITH_ID:
                return dbHelper.getReadableDatabase().query(TABLE_NAME,null,
                        COLUMN_NAME_ID,selectionArgs,null,null,null);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri returnUri = null;
        switch (matcher.match(uri)){
            case MOVIES:
            {
                long id = dbHelper.getWritableDatabase().insert(TABLE_NAME,null,contentValues);
                if(id > 0){
                    returnUri = ContentUris.withAppendedId(CONTENT_URI,id);
                }
                break;
            }
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return dbHelper.getWritableDatabase().delete(TABLE_NAME,selection,selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        return dbHelper.getWritableDatabase().update(TABLE_NAME,contentValues,selection,selectionArgs);
    }
}
