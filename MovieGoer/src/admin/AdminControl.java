package admin;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import movie.Cinema;
import movie.Movie;
import movie.MovieListing.dayOfWeek;
import movie.MovieListingControl;

public class AdminControl {
	Scanner sc = new Scanner(System.in);

	public void MainMenu() throws IllegalStateException, FileNotFoundException {
		System.out.print("\n(1) Access Cineplex database (2) Edit movie database: \n");
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
		System.out.println("isPlatnimum: " + beans.get(c).getIP());
		for (int j = 0; j < beans.get(c).getHallID().size(); j++) {
			System.out.println("Hall " + beans.get(c).getHallID().get(j));
			System.out.println("Available Show Times: " + beans.get(c).getAST(j));
			System.out.println("Show Times: " + beans.get(c).getST(j));
		}

	}

	public void MovieMenu() throws IllegalStateException, FileNotFoundException {
		String cinemaFileName = "C:\\Users\\hue\\Desktop\\database\\cinema\\cinema.csv";
		String movieFileName = "C:\\Users\\hue\\Desktop\\database\\movie\\movie.csv";
		Cinema cinema = new Cinema();
		Movie movie = new Movie();
		List<Cinema> cinemaBeans = cinema.getCinemaList(cinemaFileName);
		List<Movie> movieBeans = movie.getMovieList(movieFileName);
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		getCinemaInfo(cinemaBeans, locationID);
		System.out.print(
				"Selection option\n(1) Show all current movie listings (2) Modify a cinema hall movie listing: ");
		int choice = sc.nextInt();

		System.out.print("Select cinema hall number (1 ~ 3): ");
		int hallID = sc.nextInt();
		System.out.print(
				"Select option\n(1) Add movie listing (2) Remove movie listing (3) Update movie listing (4) List current movie listing: ");
		MovieListingControl mc = new MovieListingControl();
		// List<MovieListing> hallMovieListing =
		// cinemaBeans.get(locationID).getMovieList(hallID);
		int option = sc.nextInt();
		switch (option) {
		case 1:
			movie.printCurrentMovieList(movieBeans);
			System.out.println("\nChoose movie to add (Number): ");
			int moviePos = sc.nextInt();
			dayOfWeek day = mc.chooseDay();
			List<String> availableTiming = cinemaBeans.get(locationID).getAST(hallID);
			System.out.println("\nChoose available showing time");
			for (int i = 0; i < availableTiming.size(); i++) {
				System.out.println(Integer.toString(i + 1) + ". " + availableTiming.get(i));
			}
			int pos = sc.nextInt() - 1;
			break;
		default:
		}
	}
}
