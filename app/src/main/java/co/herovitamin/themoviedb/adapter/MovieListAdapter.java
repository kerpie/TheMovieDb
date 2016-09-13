package co.herovitamin.themoviedb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import co.herovitamin.themoviedb.R;
import co.herovitamin.themoviedb.network.model.MovieInList;
import co.herovitamin.themoviedb.viewholder.MovieItemViewHolder;

public class MovieListAdapter extends RecyclerView.Adapter<MovieItemViewHolder>{

    MovieInList[] items;

    public MovieListAdapter(MovieInList[] items) {
        this.items = items;
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieItemViewHolder(
                LayoutInflater
                        .from(
                                parent.getContext())
                        .inflate(R.layout.movie_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        holder.bind(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

}