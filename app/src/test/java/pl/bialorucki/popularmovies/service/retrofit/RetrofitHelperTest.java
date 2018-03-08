package pl.bialorucki.popularmovies.service.retrofit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */
public class RetrofitHelperTest {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    @Test
    public void should_not_be_null(){
        MovieRetrofitService retrofitMoviesClient = RetrofitHelper.createRetrofitMoviesClient();
        assertNotNull(retrofitMoviesClient);
    }
}