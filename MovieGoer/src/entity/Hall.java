package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

/**
 * Represents a single cinema hall in a cineplex
 * 
 * @author SS4 Group 4
 *
 */
public class Hall {
	Scanner sc = new Scanner(System.in);
	/**
	 * The ID of the cinema Hall
	 */
	@CsvBindByName
	protected int hallID;
	/**
	 * Whether cinema hall is platinum
	 */
	@CsvBindByName
	private boolean isPlatinum;
	/**
	 * List of available show times
	 */
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> availableShowTimes;
	/**
	 * 2D array of seats in a cinema hall, initialised to 9x13
	 */
	private Seat[][] seat = new Seat[9][13];

	/**
	 * Creates an empty Hall
	 */
	public Hall() {
	}

	/**
	 * Gets the ID of the hall
	 * 
	 * @return The Hall ID
	 */
	public int getHallID() {
		return this.hallID;
	}

	/**
	 * Gets the list of all the MovieListings
	 * 
	 * @return The list of MovieListing
	 */

	/**
	 * Gets a list of available showtimes
	 * 
	 * @return List of available showtimes
	 */
	public List<String> getAvailableShowTimes() {
		return availableShowTimes;
	}

	/**
	 * Changes the ID of the cinema hall
	 * 
	 * @param split New ID of the cinema hall
	 */
	public void setHallID(int split) {
		this.hallID = split;
	}

	/**
	 * Changes the list of available showtimes to a newly created list
	 * 
	 * @param ast String containing all the available showtimes, separated by a
	 *            semicolon
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
	 * Display all the seats and their availability
	 */
	public void showSeats() {
		char base = '@';
		System.out.println("===================Screen================");
		for (int i = 1; i < 10; i++) {
			char rowLetter = (char) (base + i);
			System.out.printf("%c ", rowLetter);
			for (int j = 1; j < 14; j++) {
				if (j == 7) {
					System.out.printf("   ");
				} else {
					// first two rows for couple seats
					if (i == 0 || i == 1) {
						System.out.printf(ConsoleColors.PURPLE_BACKGROUND_BRIGHT + "%s" + ConsoleColors.RESET,
								seat[i - 1][j - 1].seatSlot());
					}
					// last two rows for elite seats
					if (i == 8 || i == 9) {
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
	 * Assign a seat to a customer or print error message if seat has previously
	 * been assigned
	 * 
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

	/**
	 * Changes platinum status of the cinema hall
	 * 
	 * @param plat New platinum status of the cinema hall
	 */
	public void setIP(boolean plat) {
		this.isPlatinum = plat;
	}

	/**
	 * Gets the platinum status of the cinema
	 * 
	 * @return Platinum status
	 */
	public boolean getIP() {
		return this.isPlatinum;
	}
}
