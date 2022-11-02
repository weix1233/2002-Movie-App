package movie;

import java.io.FileNotFoundException;
import java.util.List;

public interface MovieListingInterface {

	public List<Movie> listMovieTitles() throws IllegalStateException, FileNotFoundException;

	public MovieListing addMovieListing(MovieListing listing);

	public void removeMovieListing(MovieListing listing);

	public void updateMovieListing(List<MovieListing> beans, int movieListingID);
}
