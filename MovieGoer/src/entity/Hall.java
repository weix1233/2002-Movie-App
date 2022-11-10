package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
/**
 * Represents a single cinema hall in a cineplex
 * @author SS4 Group 4
 *
 */
public class Hall {
	/**
	 * The ID of the cinema Hall
	 */
	@CsvBindByName
	protected int hallID;
	/**
	 * List of available show times
	 */
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> availableShowTimes;
	/**
	 * List of all show times
	 */
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> showTimes;
	/**
	 * List of MovieListing objects
	 */
	private List<MovieListing> movieListing = new ArrayList<MovieListing>();
	/**
	 * 2D array of seats in a cinema hall, initialised to 9x13
	 */
	private Seat[][] seat = new Seat[9][13];
	/**
	 * Creates an empty Hall
	 */
	public Hall() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Gets the ID of the hall
	 * @return The Hall ID
	 */
	public int getHallID() {
		return this.hallID;
	}
	/**
	 * Gets the list of all the MovieListings
	 * @return The list of MovieListing
	 */
	public List<MovieListing> getMovieListing() {
		return movieListing;
	}
	/**
	 * Gets a list of available showtimes
	 * @return List of available showtimes
	 */
	public List<String> getAvailableShowTimes() {
		return availableShowTimes;
	}
	/**
	 * Gets a list of all showtimes
	 * @return List of all showtimes
	 */
	public List<String> getShowTimes() {
		return showTimes;
	}
	/**
	 * Changes the ID of the cinema hall
	 * @param split New ID of the cinema hall
	 */
	public void setHallID(int split) {
		this.hallID = split;
	}
	/**
	 * Changes the list of available showtimes to a newly created list 
	 * @param ast String containing all the available showtimes, separated by a semicolon
	 */
	public void setAvailableShowTimes(String ast) {
		List<String> s = new ArrayList<String>();
		String[] split = ast.split(";");
		for (int i = 0; i < split.length; i++)
			s.add(split[i]);
		Collections.sort(s);
		this.availableShowTimes = s;
	}
	/**
	 * Changes the list of all showtimes to a newly created list 
	 * @param st String containing all the showtimes, separated by a semicolon
	 */
	public void setShowTimes(String st) {
		List<String> s = new ArrayList<String>();
		String[] split = st.split(";");
		if (!split[0].equals("")) {
			for (int i = 0; i < split.length; i++)
				s.add(split[i]);
		}
		this.showTimes = s;
	}
	/**
	 * Delete a movie listing given it's position in the list and update the showtimes
	 * @param movieListPosition Position of the movie listing in the list
	 */
	public void delMovieListing(int movieListPosition) {
		String tempShowTime = showTimes.remove(movieListPosition);
		availableShowTimes.add(tempShowTime);
		Collections.sort(availableShowTimes);
		movieListing.remove(movieListPosition);
	}
	/**
	 * Display all the seats and their availability
	 */
	public void showSeats() {
		char base = 'A';
		Scanner sc = new Scanner(System.in);
		System.out.println("===================Screen================");
		for (int i = 0; i < 9; i++) {
			char rowLetter = (char) (base + i);
			System.out.printf("%c ", rowLetter);
			for (int j = 0; j < 13; j++) {
				if (j == 6) {
					System.out.printf("   ");
				} else {
					// first two rows for couple seats
					if (i == 0 || i == 1) {
						System.out.printf(ConsoleColors.PURPLE_BACKGROUND_BRIGHT + "%s" + ConsoleColors.RESET,
								seat[i - 1][j - 1].seatSlot());
					}
					// last two rows for elite seats
					if (i == 7 || i == 8) {
						System.out.printf(ConsoleColors.YELLOW_BACKGROUND + "%s" + ConsoleColors.RESET,
								seat[i - 1][j - 1].seatSlot());
					} else {
						System.out.printf("%s", seat[i - 1][j - 1].seatSlot());
					}
				}
			}
			System.out.printf("\n");
		}
		System.out.println("=================Entrance================");
	}
	/**
	 * Assign a seat to a customer or print error message if seat has previously been assigned
	 * @param row Row where the seat is
	 * @param col Column to a seat
	 */
	public void updateSeats(int row, int col) {
		if (!seat[row - 1][col - 1].isOccupied()) {
			seat[row - 1][col - 1].assign();
			System.out.println("Seat assigned!");
		} else
			System.out.println("Seat already assigned to a customer.");
	}
}
