package movie;

import java.util.List;

public abstract class Cinema {
	protected String cinemaID;
	protected String name;
	protected List<MovieListing> mList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCinemaID() {
		return cinemaID;
	}
	public void setCinemaID(String cinemaID) {
		this.cinemaID = cinemaID;
	}
	public abstract List<MovieListing>initialiseData();
}