package movie;

import java.util.ArrayList;
import java.util.List;

public class BoonLay extends Cinema{
	public BoonLay() {
		this.name = "Boon Lay";
		this.cinemaID = "002";
		this.mList = initialiseData();
	}
	@Override
	public List<MovieListing> initialiseData() {
		List<MovieListing> movieList = new ArrayList<MovieListing>();
		return movieList;
	}
}
