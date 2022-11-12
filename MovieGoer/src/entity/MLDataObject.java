package entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
/**
 * Temporary object to convert into MovieListing
 * @author SS4 Group 4
 *
 */
public class MLDataObject {
	/**
	 * Type of screen
	 */
	@CsvBindByName
	public String type;
	/**
	 * Day of the screening
	 */
	@CsvBindByName
	public String day;
	/**	
	 * Movie object associated with the MovieListing
	 */
	@CsvRecurse
	private Movie movie;
	/**
	 * Time of the screening
	 */
	@CsvBindByName
	private String showTime;
	/**
	 * ID of the hall where the movie will be screened
	 */
	@CsvBindByName
	private int hallID;
	/**
	 * Constructor for MLDataObject
	 * 
	 * @param type type of screen
	 * @param day day of screening
	 * @param movie movie object associated with the MovieListing
	 * @param showTime time of the screening
	 * @param id ID of the hall where the movie will be screened
	 */
	public MLDataObject(String type, String day, Movie movie,String showTime,int id) {
		this.type = type;
		this.day = day;
		this.movie = movie;
		this.showTime = showTime;
		this.hallID = id;
	}
	/**
	 * Empty constructor for openCSV
	 */
	public MLDataObject() {}
	/**
	 * Gets screen type
	 * @return type of screen
	 */
	public String getScreenType() {
		return this.type;
	}
	/**
	 * Gets day of the week
	 * @return day of the week
	 */
	public String getDayOfWeek() {
		return this.day;
	}
	/**
	 * Gets the movie object
	 * @return movie associated with the listing
	 */
	public Movie getMovie() {
		return this.movie;
	}
	/**
	 * Gets the show time of the listing
	 * @return show time of the listing
	 */
	public String getShowTime() {
		return this.showTime;
	}
	/**
	 * Gets the id of the hall
	 * @return the id of the hall
	 */
	public int getHallID() {
		return this.hallID;
	}
	/**
	 * Changes the screen type
	 * @param st the desired screen type
	 */
	public void setType(String st) {
		this.type = st;
	}
	/**
	 * Changes the day
	 * @param day the desired day
	 */
	public void setDayOfWeek(String day) {
		this.day = day;
	}
	/**
	 * Changes the movie object associated with the listing
	 * @param movie the desired movie object
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	/**
	 * Changes the show time of the listing
	 * @param st the desired show time object
	 */
	public void setShowTime(String st) {
		this.showTime = st;
	}
	/**
	 * Changes the hall
	 * @param id desired id of the hall
	 */
	public void setHallID(int id) {
		this.hallID = id;
	}
}

