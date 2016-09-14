package co.herovitamin.themoviedb.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.herovitamin.themoviedb.MainActivity;
import co.herovitamin.themoviedb.R;
import co.herovitamin.themoviedb.fragment.MovieDetailFragment;
import co.herovitamin.themoviedb.network.model.MovieSummaryInList;

public class MovieItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.movie_image)
    ImageView mMovieImage;

    @BindView(R.id.movie_title)
    TextView mMovieTitle;

    @BindView(R.id.movie_release_date)
    TextView mMovieReleaseDate;

    MovieSummaryInList movie;

    public MovieItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(MovieSummaryInList movieSummaryInList){

        movie = movieSummaryInList;

        mMovieTitle.setText(movieSummaryInList.getOriginalTitle());
        mMovieReleaseDate.setText(mMovieReleaseDate.getResources().getString(R.string.placeholder_release_date) + movieSummaryInList.getFormattedDate());
        Picasso
                .with(mMovieImage.getContext())
                .load("http://image.tmdb.org/t/p/w185/" + movieSummaryInList.getPosterPath())
                .into(mMovieImage);
    }

    @Override
    public void onClick(View v) {
        ((MainActivity)v.getContext()).replaceFragment(MovieDetailFragment.newInstance(movie.getId()), true);
    }
}