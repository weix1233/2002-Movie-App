package entity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import boundary.MovieListingControl;
import control.ReadCSVFiles;
import control.TextToHall;
import entity.MovieListing.dayOfWeek;
import entity.MovieListing.screenType;

/**
 * Represents a single cinema location
 * 
 * @author SS4 Group 4
 *
 */
public class Cinema {
	Scanner sc = new Scanner(System.in);
	/**
	 * Name of the cinema
	 */
	@CsvBindByName
	private String name;
	/**
	 * ID of the cinema
	 */
	@CsvBindByName
	private String cinemaID;
	/**
	 * List of Hall numbers in this cinema
	 */
	@CsvBindAndSplitByName(elementType = Hall.class, collectionType = ArrayList.class, splitOn = ",", converter = TextToHall.class)
	private List<Hall> halls;
	/**
	 * List of all the movie listings in the cinema
	 */
	private List<MovieListing> fullML = new ArrayList<MovieListing>();

	/**
	 * Creates a new Cinema with the following parameters
	 * 
	 * @param halls List of the cinema halls
	 * @param name  Name of the cinema
	 * @param cid   ID of the cinema
	 */
	public Cinema(String name, String cid) {
		this.name = name;
		this.cinemaID = cid;
	}

	/**
	 * Creates an empty Cinema object
	 */
	public Cinema() {
	}

	/**
	 * Gets the name of the cinema
	 * 
	 * @return The name of the cinema
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of the cinema
	 * 
	 * @param name New name of the cinema
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets a list of hall IDs for the cinema
	 * 
	 * @return list of hall IDs
	 *
	 * 
	 *         /** Gets a Hall given the ID of the hall
	 * 
	 * @param hallID ID of the hall
	 * @return Hall object with the specified hall ID
	 */

	/**
	 * Changes ID of the cinema
	 * 
	 * @param cid New cinema ID
	 */
	public void setCinemaID(String cid) {
		this.cinemaID = cid;
	}

	/**
	 * Gets the ID of the cinema
	 * 
	 * @return The cinema ID
	 */
	public String getCinemaID() {
		return this.cinemaID;
	}

	/**
	 * Sets the list of movie listings
	 * 
	 * @param locID ID of the cinema
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public void setMovieListing(int locID) throws IllegalStateException, FileNotFoundException {
		this.fullML = ReadCSVFiles.initialiseML(locID);
		for (int i = 0; i < fullML.size(); i++) {
			String date = fullML.get(i).getDay().name();
			String showtime = fullML.get(i).getShowtime();
			String cat = date + " " + showtime;
			int hallID = fullML.get(i).getHallID();
			halls.get(hallID).getAvailableShowTimes().remove(cat);
		}
	}

	/**
	 * Gets the list of all movie listings
	 * 
	 * @return List of all movie listings
	 */
	public List<MovieListing> getMovieListing() {
		return this.fullML;
	}

	public void addMovieListing(List<Movie> beans, String options) {
		MovieListingControl mc = new MovieListingControl();
		String[] parts = options.split(" ");
		int moviePos;
		int check = 0;
		do {
			System.out.println("\nChoose movie to add (Number): ");
			moviePos = sc.nextInt();
			for (int i = 0; i < parts.length; i++) {
				if (Integer.toString(moviePos).equals(parts[i])) {
					check++;
					break;
				}
			}
			System.out.print("---- Error! Select only shown options ----");
		} while (check == 0);

		Movie selectedMovie = beans.get(moviePos);
		System.out.print("Select cinema hall number (1 ~ 3): ");
		int hallID = sc.nextInt();
		int checkAvailable = 0;
		dayOfWeek day;
		List<String> hallAvailableShowTimes;
		do {
			day = mc.chooseDay();
			String strDay = day.name();
			System.out.println("---- Available time slots ----");
			hallAvailableShowTimes = halls.get(hallID).getAvailableShowTimes();
			for (int i = 0; i < hallAvailableShowTimes.size(); i++) {
				if (hallAvailableShowTimes.get(i).split(" ")[0].equals(strDay)) {
					checkAvailable++;
					System.out.println(Integer.toString(i) + ". " + hallAvailableShowTimes.get(i));
				}
			}
			if (checkAvailable == 0) {
				System.out.println(strDay + " has no more available time slots. Please pick another day\n");
			} else {
				System.out.print("\nChoose available showing time: ");
			}
		} while (checkAvailable == 0);

		int showTimePos = sc.nextInt();
		screenType screen = mc.chooseScreenType();
		String tempShowTime = hallAvailableShowTimes.remove(showTimePos);
		MovieListing ml = new MovieListing(selectedMovie, screen, day, tempShowTime, hallID);
		fullML.add(ml);
	}

	public void delMovieListing() {
		if (fullML.size() == 0) {
			System.out.println("No current movie listings to remove");
			return;
		}
		System.out.println("Current movie listings");
		for (int i = 0; i < fullML.size(); i++) {
			System.out.println(Integer.toString(i) + ". " + fullML.get(i).getMovie().getMovieTitle() + " "
					+ fullML.get(i).getShowtime());
		}
		System.out.print("Select listing to remove: ");
		int listPos = sc.nextInt();
		String tempShowTime = fullML.get(listPos).getShowtime();
		int hallID = fullML.get(listPos).getHallID();
		halls.get(hallID).getAvailableShowTimes().add(tempShowTime);
		Collections.sort(halls.get(hallID).getAvailableShowTimes());
		fullML.remove(listPos);
	}

	public void updateMovieListing() {
		MovieListingControl mc = new MovieListingControl();
		if (fullML.size() == 0) {
			System.out.println("No current movie listings to update");
			return;
		}
		System.out.println("Current movie listings");
		System.out.println("Title \t|  Screen Type  |  Show Time  |  Age Rating");
		for (int i = 0; i < fullML.size(); i++) {
			System.out.print(Integer.toString(i) + ". ");
			fullML.get(i).printListing();
		}
		System.out.println("Select listing to update");
		int movieListPosition = sc.nextInt();
		int hallID = fullML.get(movieListPosition).getHallID();
		System.out.print("Select option\n(1) Edit screen type (2) Edit showing time (3) Exit: ");
		int editChoice = sc.nextInt();
		switch (editChoice) {
		case 1:
			fullML.get(movieListPosition).setType(mc.chooseScreenType());
			break;
		case 2:
			String showDay = mc.chooseDay().name();
			List<String> hallAvailableShowTime = halls.get(hallID).getAvailableShowTimes();
			System.out.println("\nChoose available showing time");
			for (int i = 0; i < hallAvailableShowTime.size(); i++) {
				if (hallAvailableShowTime.get(i).split(" ")[0].equals(showDay)) {
					System.out.println(Integer.toString(i) + ". " + hallAvailableShowTime.get(i));
				}
			}
			int showTimePos = sc.nextInt();
			String tempShowTime = hallAvailableShowTime.remove(showTimePos);
			hallAvailableShowTime.add(fullML.get(movieListPosition).getShowtime());
			fullML.get(movieListPosition).setShowtime(tempShowTime);
			break;
		default:
			break;
		}
	}

	public void listMovieListing() {
		if (fullML.size() == 0) {
			System.out.println("No current movie listings available");
			return;
		}
		System.out.println("Current movie listings");
		System.out.println("Title \t | Synopis \t | Director \t | Cast \t | Age Rating \t | Day | Show Time | Hall");
		for (int i = 0; i < fullML.size(); i++) {
			System.out.print(Integer.toString(i) + ". ");
			fullML.get(i).printListing();
		}
	}

	public List<Hall> getHalls() {
		return halls;
	}
}
