package entity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import boundary.MovieListingControl;
import control.TextToHall;
/**
 * Represents a single cinema location
 * @author SS4 Group 4
 *
 */
public class Cinema {
	/**
	 * Name of the cinema
	 */
	@CsvBindByName
	private String name;
	/**
	 * List of the halls in the cinema
	 */
	@CsvBindAndSplitByName(elementType = Hall.class, collectionType = ArrayList.class, splitOn = ",", converter = TextToHall.class)
	private List<Hall> halls;
	/**
	 * ID of the cinema
	 */
	@CsvBindByName
	private String cinemaID;
	/**
	 * List of all the movie listings in the cinema
	 */
	private List<MovieListing> fullML;
	/**
	 * Creates a new Cinema with the following parameters
	 * @param halls List of the cinema halls
	 * @param name Name of the cinema
	 * @param cid ID of the cinema
	 */
	public Cinema(List<Hall> halls, String name, String cid) {
		this.name = name;
		this.halls = halls;
		this.cinemaID = cid;
	}
	/**
	 * Creates an empty Cinema object
	 */
	public Cinema() {
	}
	/**
	 * Gets the name of the cinema
	 * @return The name of the cinema
	 */
	public String getName() {
		return name;
	}
	/**
	 * Changes the name of the cinema
	 * @param name New name of the cinema
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets a list of hall IDs for the cinema
	 * @return list of hall IDs
	 */
	public List<Integer> getHallID() {
		List<Integer> hallID = new ArrayList<Integer>();
		for (int i = 0; i < halls.size(); i++) {
			hallID.add(halls.get(i).getHallID());
		}
		return hallID;
	}
	/**
	 * Gets a Hall given the ID of the hall
	 * @param hallID ID of the hall
	 * @return Hall object with the specified hall ID
	 */
	public Hall getHall(int hallID) {
		return halls.get(hallID);
	}

	/**
	 * Changes ID of the cinema
	 * @param cid New cinema ID
	 */
	public void setCinemaID(String cid) {
		this.cinemaID = cid;
	}
	/**
	 * Gets the ID of the cinema
	 * @return The cinema ID
	 */
	public String getCinemaID() {
		return this.cinemaID;
	}
	/**
	 * Sets the list of movie listings
	 * @param locID ID of the cinema
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public void setMovieListing(int locID) throws IllegalStateException, FileNotFoundException {
		MovieListingControl mc = new MovieListingControl();
		this.fullML = mc.initialiseML(locID);
		
	}
	/**
	 * Gets the list of all movie listings 
	 * @return List of all movie listings
	 */
	public List<MovieListing> getMovieListing(){
		return this.fullML;
	}
}
