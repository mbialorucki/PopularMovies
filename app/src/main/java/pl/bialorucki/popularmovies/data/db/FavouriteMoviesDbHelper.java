package pl.bialorucki.popularmovies.data.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pl.bialorucki.popularmovies.data.db.FavouriteMoviesContract.FavoriteEntry;

/**
 * Created by Maciej Bialorucki on 18.04.18.
 */
public class FavouriteMoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "waitlist.db";
    private static final int DATABASE_VERSION = 3;
    private Context context;

    public FavouriteMoviesDbHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public FavouriteMoviesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public FavouriteMoviesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, errorHandler);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVOURITES_TABLE = "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteEntry.COLUMN_NAME_ID + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_NAME_TITLE + " TEXT  NOT NULL," +
                FavoriteEntry.COLUMN_NAME_POSTER_PATH + " TEXT  NOT NULL," +
                FavoriteEntry.COLUMN_NAME_BACKDROP_PATH + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_NAME_RELEASE_DATE + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_NAME_AVG_RATING + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_NAME_ORIGINAL_LANGUAGE + " TEXT NOT NULL," +
                FavoriteEntry.COLUMN_NAME_NUMBER_OF_VOTES + " TEXT NOT NULL,"+
                FavoriteEntry.COLUMN_NAME_FAVOURITE + " INTEGER NOT NULL );";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
