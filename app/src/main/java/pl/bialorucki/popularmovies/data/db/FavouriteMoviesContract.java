package pl.bialorucki.popularmovies.data.db;

import android.provider.BaseColumns;

/**
 * Created by Maciej Bialorucki on 18.04.18.
 */
public class FavouriteMoviesContract {

    private FavouriteMoviesContract(){}

    public static class FavoriteEntry implements BaseColumns{
        public static final String TABLE_NAME = "favouriteMovies";
        public static final String COLUMN_NAME_ID = "movieId";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_POSTER_PATH = "posterPath";
        public static final String COLUMN_NAME_BACKDROP_PATH = "backdropPath";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_NAME_AVG_RATING = "avgRating";
        public static final String COLUMN_NAME_ORIGINAL_LANGUAGE = "oryginalLanguage";
        public static final String COLUMN_NAME_NUMBER_OF_VOTES = "numberOfVotes";
        public static final String COLUMN_NAME_FAVOURITE = "favourite";
    }
}
