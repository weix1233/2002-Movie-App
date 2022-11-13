package entity;

import java.util.List;

/**
 * Represents a single movie listing
 * 
 * @author SS4 Group 4
 *
 */
public class MovieListing {
	/**
	 * Type of screen: Whether it's 2d or 3d
	 * 
	 * @author SS4 Group 4
	 *
	 */
	public enum screenType {
		TWO_D, THREE_D
	};

	/**
	 * Day of the week or whether it's a public holiday
	 * 
	 * @author SS4 Group 4
	 *
	 */
	public enum dayOfWeek {
		MON, TUES, WED, THURS, FRI, SAT, SUN, PH
	};

	/**
	 * Movie object associated with the MovieListing
	 */
	private Movie movie;
	/**
	 * Type of screen
	 */
	private screenType type;
	/**
	 * Day of the screening
	 */
	private dayOfWeek day;
	/**
	 * Time of the screening
	 */
	private String showtime;
	/**
	 * ID of the hall where the movie will be screened
	 */
	private int hallID;
	/**
	 * Seats that are taken based on seat ID no.
	 */
	private List<Integer> seats;
	/**
	 * Creates a new MovieListing with the following parameters
	 * 
	 * @param movie    Movie that is being screened
	 * @param type     Type of screen
	 * @param day      Day of the screening
	 * @param showtime Time of the screening
	 * @param hallID   ID of the hall where movie will be screened
	 */
	public MovieListing(Movie movie, screenType type, dayOfWeek day, String showtime, int hallID) {
		this.movie = movie;
		this.type = type;
		this.day = day;
		this.showtime = showtime;
		this.hallID = hallID;
	}

	public MovieListing() {
	}

	/**
	 * Print the details of the movie listing
	 */
	public void printListing() {
		System.out.printf("Title: %s | Age Rating: %s | Day: %s | Show Time: %s | Hall: %s\n", movie.getMovieTitle(),
				movie.getAgeRating(), day.name(), showtime, Integer.toString(hallID));
	}

	/**
	 * Gets the Movie that is showing
	 * 
	 * @return
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * Changes the Movie that is showing to a new Movie
	 * 
	 * @param movie The new Movie object
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * Gets the type of screen
	 * 
	 * @return The screen type
	 */
	public screenType getType() {
		return type;
	}

	/**
	 * Changes the type of screen
	 * 
	 * @param type The new screen type
	 */
	public void setType(screenType type) {
		this.type = type;
	}

	/**
	 * Gets the day of the movie listing
	 * 
	 * @return Day of listing
	 */
	public dayOfWeek getDay() {
		return day;
	}

	/**
	 * Changes the day of the movie listing
	 * 
	 * @param day New day of screening
	 */
	public void setDay(dayOfWeek day) {
		this.day = day;
	}

	/**
	 * Gets the showtime of the movie
	 * 
	 * @return The movie's showtime
	 */
	public String getShowtime() {
		return showtime;
	}

	/**
	 * Changes the showtime of the movie
	 * 
	 * @param showtime The new movie showtime
	 */
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	/**
	 * Gets the ID of the cinema hall where the movie is screening
	 * 
	 * @return The hall's ID
	 */
	public int getHallID() {
		return hallID;
	}

	/**
	 * Changes the cinema hall to a new one
	 * 
	 * @param hallID ID of the new cinema hall
	 */
	public void setCinemaHall(int hallID) {
		this.hallID = hallID;
	}
	/**
	 * Changes the occupied seats to a new one
	 * @param seats The seats that are occupied
	 */
	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}
	/**
	 * Gets the seats that are occupied based on ID
	 * @return seats occupied based on ID
	 */
	public List<Integer> getSeats(){
		return this.seats;
	}
}
