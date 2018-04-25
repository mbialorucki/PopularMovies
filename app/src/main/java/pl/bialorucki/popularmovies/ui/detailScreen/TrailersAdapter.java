package pl.bialorucki.popularmovies.ui.detailScreen;

import android.net.Uri;
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
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.model.Trailer;
import pl.bialorucki.popularmovies.model.TrailerList;
import pl.bialorucki.popularmovies.ui.helper.ImageLoader;
import pl.bialorucki.popularmovies.ui.helper.PicassoImageLoader;

/**
 * Created by Maciej Bialorucki on 13.04.18.
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {


    private final OnTrailerClickListener listener;
    private List<Trailer> trailers;

    public TrailersAdapter(List<Trailer> trailers,OnTrailerClickListener listener){
        this.trailers = trailers;
        this.listener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_trailer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.bind(trailer);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public void setTrailers(TrailerList trailers) {
        this.trailers = trailers.getTrailers();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Trailer trailer){
            title.setText(trailer.getName());
            String thumbnailUrl = "http://img.youtube.com/vi/"+trailer.getKey()+"/0.jpg";
            ImageLoader loader = new PicassoImageLoader();
            loader.loadImage(thumbnail.getContext(),thumbnailUrl,thumbnail);
            thumbnail.setOnClickListener(view -> listener.onTrailerClick(trailer));
        }


    }
}
