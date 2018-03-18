package pl.bialorucki.popularmovies.service.retrofit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Maciej Bialorucki on 08.03.18.
 */
public class RetrofitHelperTest {

    @Test
    public void should_not_be_null(){
        MovieRetrofitService retrofitMoviesClient = RetrofitHelper.createRetrofitMoviesClient();
        assertNotNull(retrofitMoviesClient);
    }
}