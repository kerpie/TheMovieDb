package co.herovitamin.themoviedb.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.herovitamin.themoviedb.R;
import co.herovitamin.themoviedb.adapter.MovieListAdapter;
import co.herovitamin.themoviedb.network.API;
import co.herovitamin.themoviedb.network.model.MovieListResponse;
import co.herovitamin.themoviedb.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends Fragment implements Callback<MovieListResponse> {

    @BindView(R.id.movie_list)
    RecyclerView mMovieList;

    API mApiService;

    ProgressDialog mLoadingDialog;

    public MovieListFragment() {
    }

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = Util.createNetworkClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        mLoadingDialog = new ProgressDialog(getContext());
        mLoadingDialog.setMessage(getResources().getString(R.string.placeholder_loading));

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMovieList.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();



        mLoadingDialog.show();

        Call<MovieListResponse> response = mApiService.getUpcomingMovies();
        response.enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
        if(response.isSuccessful()){
            MovieListResponse movieListResponse = response.body();

            MovieListAdapter adapter = new MovieListAdapter(movieListResponse.getResults());
            mMovieList.setAdapter(adapter);
        }
        mLoadingDialog.dismiss();
    }

    @Override
    public void onFailure(Call<MovieListResponse> call, Throwable t) {
        Toast.makeText(getActivity(), R.string.connection_error, Toast.LENGTH_SHORT).show();
        mLoadingDialog.dismiss();
    }
}