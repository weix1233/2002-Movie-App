package movie;

import java.util.ArrayList;
import java.util.List;

public class Orchard extends Cinema{
	public Orchard() {
		this.name = "Orchard";
		this.cinemaID = "003";
		this.mList = initialiseData();
	}
	@Override
	public List<MovieListing> initialiseData() {
		List<MovieListing> movieList = new ArrayList<MovieListing>();
		return movieList;
	}
}
