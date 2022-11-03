package movie;

import java.util.ArrayList;
import java.util.List;

public class Jurong extends Cinema{
	public Jurong() {
		this.name = "Jurong";
		this.cinemaID = "001";
		this.mList = initialiseData();
	}
	@Override
	public List<MovieListing> initialiseData() {
		List<MovieListing> movieList = new ArrayList<MovieListing>();
		return movieList;
	}
}
