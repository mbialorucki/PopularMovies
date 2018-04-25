package pl.bialorucki.popularmovies.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import org.junit.Test;

import java.util.List;

import pl.bialorucki.popularmovies.model.Movie;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Maciej Bialorucki on 18.04.18.
 */
public class SQLiteRepositoryTest {


    FavouriteMoviesDbHelper dbHelper = mock(FavouriteMoviesDbHelper.class);

    @Test
    public void should_return_empty_list_for_new_database(){
        FavouriteMoviesRepository repository = new SQLiteRepository(dbHelper);

        List<Movie> all = repository.getAll();

        assertEquals(all.size(),0);
    }
}