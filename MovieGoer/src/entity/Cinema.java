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
	/**
	 * Scanner object
	 */
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
	 * @param name Name of the cinema
	 * @param cid  ID of the cinema
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

	/**
	 * Adds a new movie listing to the cinema's List of MovieListing
	 * 
	 * @param beans   List of movie listings
	 * @param options Options for movie
	 */
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
			if (check == 0) {
				System.out.print("---- Error! Select only shown options ----");
			}
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
		String[] tempShowTime = hallAvailableShowTimes.remove(showTimePos).split(" ");
		MovieListing ml = new MovieListing(selectedMovie, screen, day, tempShowTime[1], hallID);
		fullML.add(ml);
	}

	/**
	 * Delete an existing movie listing from the cinema's List of MovieListing
	 */
	public void delMovieListing() {
		if (fullML.size() == 0) {
			System.out.println("No current movie listings to remove");
			return;
		}
		System.out.println("Current movie listings");
		for (int i = 0; i < fullML.size(); i++) {
			System.out.println(Integer.toString(i) + ". " + fullML.get(i).getMovie().getMovieTitle() + " " + " "
					+ fullML.get(i).getDay().name() + " " + fullML.get(i).getShowtime());
		}
		int listPos;
		do {
			System.out.print("Select listing to remove: ");
			listPos = sc.nextInt();
			if (listPos < 0 || listPos >= fullML.size()) {
				System.out.println("---- Error! Please select listed option");
			}
		} while (listPos < 0 || listPos >= fullML.size());

		String tempShowTime = fullML.get(listPos).getShowtime();
		int hallID = fullML.get(listPos).getHallID();
		halls.get(hallID).getAvailableShowTimes().add(tempShowTime);
		Collections.sort(halls.get(hallID).getAvailableShowTimes());
		fullML.remove(listPos);
	}

	/**
	 * Update an existing movie listing details in the cinema's List of MovieListing
	 */
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
		int movieListPosition;
		do {
			System.out.println("Select listing to update");
			movieListPosition = sc.nextInt();
			if (movieListPosition < 0 || movieListPosition >= fullML.size()) {
				System.out.println("---- Error! Select from listed options ----");
			}
		} while (movieListPosition < 0 || movieListPosition >= fullML.size());

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
			String check = "";
			for (int i = 0; i < hallAvailableShowTime.size(); i++) {
				if (hallAvailableShowTime.get(i).split(" ")[0].equals(showDay)) {
					System.out.println(Integer.toString(i) + ". " + hallAvailableShowTime.get(i));
					check += Integer.toString(i) + " ";
				}
			}
			int showTimePos;
			String split[] = check.split(" ");
			int pass = 0;
			do {
				showTimePos = sc.nextInt();
				for (int i = 0; i < split.length; i++) {
					if (split[i].equals(Integer.toString(showTimePos))) {
						pass++;
						break;
					}
				}
				if (pass == 0) {
					System.out.println("---- Error! Select from listed options ----");
				}
			} while (pass == 0);
			String[] tempShowTime = hallAvailableShowTime.remove(showTimePos).split(" ");
			dayOfWeek tempDay = dayOfWeek.valueOf(tempShowTime[0]);
			String oldShowTime = fullML.get(movieListPosition).getDay().name() + " "
					+ fullML.get(movieListPosition).getShowtime();
			hallAvailableShowTime.add(oldShowTime);
			fullML.get(movieListPosition).setShowtime(tempShowTime[1]);
			fullML.get(movieListPosition).setDay(tempDay);
			break;
		default:
			break;
		}
	}

	/**
	 * Prints out all the Movie Listing objects in the cinema's List of Movie
	 * Listing
	 */
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

	/**
	 * Gets a list of hall IDs for the cinema
	 * 
	 * @return list of hall IDs
	 */

	public List<Hall> getHalls() {
		return halls;
	}
}
