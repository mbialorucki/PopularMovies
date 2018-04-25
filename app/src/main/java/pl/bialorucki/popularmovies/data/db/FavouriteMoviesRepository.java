package pl.bialorucki.popularmovies.data.db;

import pl.bialorucki.popularmovies.model.Movie;
import java.util.List;

/**
 * Created by Maciej Bialorucki on 18.04.18.
 */
public interface FavouriteMoviesRepository {

    List<Movie> getAll();
    void remove(Movie movie);
    void remove(String movieId);
    void add(Movie movie);
}
