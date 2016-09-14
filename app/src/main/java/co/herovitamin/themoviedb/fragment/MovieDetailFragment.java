package co.herovitamin.themoviedb.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.herovitamin.themoviedb.R;
import co.herovitamin.themoviedb.network.API;
import co.herovitamin.themoviedb.network.model.Genre;
import co.herovitamin.themoviedb.network.model.Movie;
import co.herovitamin.themoviedb.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailFragment extends Fragment implements Callback<Movie> {

    public static final String KEY_ID = "ID";

    private int mMovieId;

    API mApiService;

    @BindView(R.id.movie_detail_title)
    TextView mMovieTitle;

    @BindView(R.id.movie_detail_poster)
    ImageView mMoviePoster;

    @BindView(R.id.movie_detail_overview)
    TextView mMovieOverview;

    @BindView(R.id.movie_detail_release_date)
    TextView mMovieReleaseDate;

    @BindView(R.id.movie_detail_genres)
    TextView mMovieGenres;

    ProgressDialog mLoadingDialog;

    public MovieDetailFragment() {
    }

    public static MovieDetailFragment newInstance(int id) {
        MovieDetailFragment fragment = new MovieDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ID, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            mMovieId = getArguments().getInt(KEY_ID);
        mApiService = Util.createNetworkClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        mLoadingDialog = new ProgressDialog(getContext());
        mLoadingDialog.setMessage(getResources().getString(R.string.placeholder_loading));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Call<Movie> response = mApiService.getMovie(String.valueOf(mMovieId));
        response.enqueue(this);

    }

    @Override
    public void onResponse(Call<Movie> call, Response<Movie> response) {

        if(response.isSuccessful()){
            Movie movie = response.body();

            Picasso
                    .with(getContext())
                    .load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath())
                    .into(mMoviePoster);

            mMovieTitle.setText(movie.getTitle());
            mMovieReleaseDate.setText(getResources().getString(R.string.placeholder_release_date) + movie.getReleaseDate());
            mMovieOverview.setText(movie.getOverview());

            String genres = getResources().getString(R.string.placeholder_genres);

            for (int i = 0; i < movie.getGenres().length; i++) {
                genres += movie.getGenres()[i].getName();
                if(i != movie.getGenres().length - 1){
                    genres += ", ";
                }
            }

            mMovieGenres.setText(genres);
        }

        mLoadingDialog.dismiss();
    }

    @Override
    public void onFailure(Call<Movie> call, Throwable t) {
        Toast.makeText(getActivity(), R.string.connection_error, Toast.LENGTH_SHORT).show();
        mLoadingDialog.dismiss();
    }
}