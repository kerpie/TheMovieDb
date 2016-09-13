package co.herovitamin.themoviedb.network.model;

public class MovieListResponse {

    private MovieInList[] results;

    public MovieListResponse(MovieInList[] results) {
        this.results = results;
    }

    public MovieInList[] getResults() {
        return results;
    }

    public void setResults(MovieInList[] results) {
        this.results = results;
    }
}
