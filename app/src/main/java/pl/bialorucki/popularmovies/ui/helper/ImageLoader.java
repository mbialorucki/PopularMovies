package pl.bialorucki.popularmovies.ui.helper;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Maciej Bialorucki on 16.03.18.
 */

public interface ImageLoader {

    void loadImage(Context context, String imageURL,ImageView destination);
}
