package co.herovitamin.themoviedb.network;

import co.herovitamin.themoviedb.network.model.Movie;
import co.herovitamin.themoviedb.network.model.MovieListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("movie/upcoming?api_key=1f54bd990f1cdfb230adb312546d765d")
    Call<MovieListResponse> getUpcomingMovies();

    @GET("movie/{id}?api_key=1f54bd990f1cdfb230adb312546d765d")
    Call<Movie> getMovie(@Path("id") String id);
}
