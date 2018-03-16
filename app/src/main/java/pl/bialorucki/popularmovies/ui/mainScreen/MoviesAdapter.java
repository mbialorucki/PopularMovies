package pl.bialorucki.popularmovies.ui.mainScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.Utils;
import pl.bialorucki.popularmovies.model.Movie;
import pl.bialorucki.popularmovies.ui.detailScreen.DetailActivity;
import pl.bialorucki.popularmovies.ui.helper.ImageLoader;
import pl.bialorucki.popularmovies.ui.helper.PicassoImageLoader;

/**
 * Created by Maciej Bialorucki on 06.03.18.
 */

class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies;
    private ImageLoader imageLoader;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
        imageLoader = new PicassoImageLoader();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_grid_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieCard.setOnClickListener(view -> {
            Intent intent = new Intent(holder.moviewPoster.getContext(), DetailActivity.class);
            intent.putExtra("movie",movie);
            holder.moviewPoster.getContext().startActivity(intent);
        });

        imageLoader.loadImage(holder.moviewPoster.getContext(),Utils.BASE_PATH + movies.get(position).getPoster_path(),holder.moviewPoster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_tv)
        TextView movieTitle;

        @BindView(R.id.poster_iv)
        ImageView moviewPoster;

        @BindView(R.id.movie_cv)
        CardView movieCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }
}
