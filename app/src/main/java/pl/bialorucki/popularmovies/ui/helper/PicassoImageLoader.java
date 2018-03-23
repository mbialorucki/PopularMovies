package pl.bialorucki.popularmovies.ui.helper;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Maciej Bialorucki on 16.03.18.
 */

public class PicassoImageLoader implements ImageLoader{
    @Override
    public void loadImage(Context context, String imageURL, ImageView destination) {
        Picasso.with(context)
                .load(imageURL)
                .into(destination);
    }
}
