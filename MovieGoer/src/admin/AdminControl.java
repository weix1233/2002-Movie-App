package admin;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import movie.Cinema;
import movie.Hall;
import movie.Movie;
import movie.MovieControl;
import movie.MovieListing;
import movie.MovieListing.dayOfWeek;
import movie.MovieListing.screenType;
import movie.MovieListingControl;
import movie.SortTop;

public class AdminControl {
	Scanner sc = new Scanner(System.in);

	public void MainMenu() throws IllegalStateException, FileNotFoundException {
		int c = 1;// sc.nextInt();
		switch (c) {
		case 1:
			MovieMenu();
			break;
		case 2:
			break;
		default:
			break;
		}

	}

	private void getCinemaInfo(List<Cinema> beans, int c) {
		System.out.printf("Cinema ID: %s\n", beans.get(c).getCinemaID());
		System.out.printf("Name: %s\n", beans.get(c).getName());
		System.out.println("isPlatinum: " + beans.get(c).getIP());
		for (int j = 1; j < beans.get(c).getHallID().size(); j++) {
			System.out.println("Hall " + beans.get(c).getHallID().get(j));
			// System.out.println("Available Show Times: " + beans.get(c).getAST(j));
			// System.out.println("Show Times: " + beans.get(c).getST(j));
		}

	}

	public void sortPopularMovie(List<Movie> movieBeans) {
		SortTop st = new SortTop(movieBeans);
	}

	public void menuAddMovieListing(List<Movie> beans, MovieControl movieControl, MovieListingControl mc, Hall hall) {
		movieControl.printCurrentMovieList(beans);
		System.out.println("\nChoose movie to add (Number): ");
		int moviePos = sc.nextInt();
		Movie selectedMovie = beans.get(moviePos);
		screenType screen = mc.chooseScreenType();
		dayOfWeek day = mc.chooseDay();
		String strDay = day.name();
		List<String> availableTiming = hall.getAvailableShowTimes();
		System.out.println("\nChoose available showing time");
		for (int i = 0; i < availableTiming.size(); i++) {
			if (availableTiming.get(i).split(" ")[0].equals(strDay)) {
				System.out.println(Integer.toString(i) + ". " + availableTiming.get(i));
			}
		}
		int showTimePos = sc.nextInt();
		hall.addMovieListing(selectedMovie, screen, day, showTimePos);
	}

	public void MovieDatabaseMenu(List<Movie> movieBeans, MovieControl movieControl) {
		int option;
		do {
			System.out.print(
					"Selection option\n(1) List all movies (2) Add movie (3) Delete movie (4) Update Movie (5) Exit: ");
			option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				movieControl.printMovies(movieBeans);
				break;
			case 2:
				movieControl.addMovieToDatabase(movieBeans);
				break;
			case 3:
				movieControl.printMovies(movieBeans);
				movieControl.delMovieFromDatabase(movieBeans);
				break;
			case 4: // update movie
				movieControl.updateMovieInDatabase(movieBeans);
				break;
			default:
				break;
			}
		} while (option > 0 && option < 5);

	}

	public void MovieMenu() throws IllegalStateException, FileNotFoundException {
		String cinemaFileName = "C:\\Users\\tanju\\git\\2002-Movie-App23\\MovieGoer\\database\\cinema\\cinema.csv";
		String movieFileName = "C:\\Users\\tanju\\git\\2002-Movie-App23\\MovieGoer\\database\\movie\\movie.csv";
		Cinema cinema = new Cinema();
		Movie movie = new Movie();
		MovieControl movieControl = new MovieControl();
		List<Cinema> cinemaBeans = cinema.getCinemaList(cinemaFileName);
		List<Movie> movieBeans = movieControl.getMovieList(movieFileName);
		System.out.print("Select option\n(1) Access movie database (2) Access cineplex database: ");
		if (sc.nextInt() == 1) {
			MovieDatabaseMenu(movieBeans, movieControl);
			return;
		}
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		getCinemaInfo(cinemaBeans, locationID);
		System.out.print("Select cinema hall number (1 ~ 3): ");
		int hallID = sc.nextInt();
		Hall hall = cinemaBeans.get(locationID).getHall(hallID);
		List<MovieListing> hallML = hall.getMovieListing();
		MovieListingControl mc = new MovieListingControl();
		int option;

		do {
			System.out.print(
					"Select option\n(1) Add movie showtime (2) Remove movie showtime (3) Update movie showtime (4) List current movie showtimes (5) Exit: ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				menuAddMovieListing(movieBeans, movieControl, mc, hall);
				break;
			case 2:
				if (hallML.size() == 0) {
					System.out.println("No current movie listings to remove");
					break;
				}
				System.out.println("Current movie listings for hall " + Integer.toString(hall.getHallID()));
				for (int i = 0; i < hallML.size(); i++) {
					System.out.println(Integer.toString(i) + ". " + hallML.get(i).getMovie().getMovieTitle() + " "
							+ hallML.get(i).getShowtime());
				}
				System.out.print("Select listing to remove: ");
				int listPos = sc.nextInt();
				hall.delMovieListing(listPos);
				break;
			case 3:
				if (hallML.size() == 0) {
					System.out.println("No current movie listings to update");
					break;
				}
				System.out.println("Current movie listings for hall " + Integer.toString(hall.getHallID()));
				System.out.println("Title \t|  Screen Type  |  Show Time  |  Age Rating");
				for (int i = 0; i < hallML.size(); i++) {
					System.out.print(Integer.toString(i) + ". ");
					hallML.get(i).printListing();
				}
				System.out.println("Select listing to update");
				int movieListPosition = sc.nextInt();
				screenType screen = mc.chooseScreenType();
				dayOfWeek day = mc.chooseDay();
				String strDay = day.name();
				List<String> availableTiming = hall.getAvailableShowTimes();
				System.out.println("\nChoose available showing time");
				for (int i = 0; i < availableTiming.size(); i++) {
					if (availableTiming.get(i).split(" ")[0].equals(strDay)) {
						System.out.println(Integer.toString(i) + ". " + availableTiming.get(i));
					}
				}
				int showTimePos = sc.nextInt();
				hall.updateMovieListing(movieListPosition, screen, day, showTimePos);
				break;
			default:
			}
		} while (option < 5 && option > 0);
		System.out.println("exited");
	}
}
