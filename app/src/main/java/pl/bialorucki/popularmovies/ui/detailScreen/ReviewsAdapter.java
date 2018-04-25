package pl.bialorucki.popularmovies.ui.detailScreen;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.bialorucki.popularmovies.R;
import pl.bialorucki.popularmovies.model.Review;

/**
 * Created by Maciej Bialorucki on 12.04.18.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private final OnReviewClickListener listener;
    private  List<Review> reviews;

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public ReviewsAdapter(List<Review> reviews,OnReviewClickListener listener){
        this.reviews = reviews;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_review,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.review_text)
        TextView review;
        @BindView(R.id.more_link)
        TextView more;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Review reviewToDisplay){
            author.setText(String.format("%s : %s", "Author" , reviewToDisplay.getAuthor()));
            review.setText(reviewToDisplay.getContent().trim());

          more.setOnClickListener(view -> listener.onItemClick(reviewToDisplay));
        }
    }
}
