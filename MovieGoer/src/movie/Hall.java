package movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import movie.MovieListing.dayOfWeek;
import movie.MovieListing.screenType;

public class Hall {
	@CsvBindByName
	protected int hallID;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> availableShowTimes;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> showTimes;
	private List<MovieListing> movieListing = new ArrayList<MovieListing>();
	private Seat[][] seat = new Seat[9][13];

	public Hall() {
		// TODO Auto-generated constructor stub
	}

	public int getHallID() {
		return this.hallID;
	}

	public List<MovieListing> getMovieListing() {
		return movieListing;
	}

	public List<String> getAvailableShowTimes() {
		return availableShowTimes;
	}

	public List<String> getShowTimes() {
		return showTimes;
	}

	public void setHallID(int split) {
		this.hallID = split;
	}

	public void setAvailableShowTimes(String ast) {
		List<String> s = new ArrayList<String>();
		String[] split = ast.split(";");
		for (int i = 0; i < split.length; i++)
			s.add(split[i]);
		Collections.sort(s);
		this.availableShowTimes = s;
	}

	public void setShowTimes(String st) {
		List<String> s = new ArrayList<String>();
		String[] split = st.split(";");
		if (!split[0].equals("")) {
			for (int i = 0; i < split.length; i++)
				s.add(split[i]);
		}
		this.showTimes = s;
	}

	public void addMovieListing(Movie movie, screenType type, dayOfWeek day, int showTimePos) {
		String tempShowTime = availableShowTimes.remove(showTimePos);
		System.out.println("Adding " + tempShowTime + "  to show time");
		showTimes.add(tempShowTime);
		MovieListing ml = new MovieListing(movie, type, day, tempShowTime, this.hallID);
		movieListing.add(ml);
	}

	public void delMovieListing(int movieListPosition) {
		String tempShowTime = showTimes.remove(movieListPosition);
		availableShowTimes.add(tempShowTime);
		Collections.sort(availableShowTimes);
		movieListing.remove(movieListPosition);
	}

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

	public void updateSeats(int row, int col) {
		if (!seat[row - 1][col - 1].isOccupied()) {
			seat[row - 1][col - 1].assign();
			System.out.println("Seat assigned!");
		} else
			System.out.println("Seat already assigned to a customer.");
	}
}
