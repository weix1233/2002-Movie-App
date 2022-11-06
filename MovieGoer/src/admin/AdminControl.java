package admin;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movie.Cinema;
import movie.Hall;
import movie.Movie;
import movie.MovieListing;
import movie.MovieListing.dayOfWeek;
import movie.MovieListing.screenType;
import movie.MovieListingControl;
import movie.SortTop;

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

	public void menuAddMovieListing(List<Movie> beans, Movie movie, MovieListingControl mc, Hall hall) {
		movie.printCurrentMovieList(beans);
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

	public void MovieDatabaseMenu(List<Movie> movieBeans, Movie movie) {
		int option;
		do {
			System.out.print(
					"Selection option\n(1) List all movies (2) Add movie (3) Delete movie (4) Update Movie (5) Exit: ");
			option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				movie.printMovies(movieBeans);
				break;
			case 2:
				System.out.print("Enter movie title: ");
				String movieTitle = sc.nextLine();
				System.out.print("Enter synopis title: ");
				String synopis = sc.nextLine();
				System.out.print("Enter director: ");
				String director = sc.nextLine();
				List<String> cast = new ArrayList<String>();
				while (true) {
					System.out.print("Enter cast name or type 'q' to quit: ");
					String userInput = sc.nextLine();
					if (userInput.equals("q")) {
						break;
					}
					cast.add(userInput);
				}
				System.out.print(
						"Choose showingStatus\n(1) Coming Soon (2) Preview (3) Now Showing (4) End Of Showing: ");
				int showingStatusChoice = sc.nextInt();
				String showingStatus;
				if (showingStatusChoice == 1) {
					showingStatus = "COMING_SOON";
				} else if (showingStatusChoice == 2) {
					showingStatus = "PREVIEW";
				} else if (showingStatusChoice == 3) {
					showingStatus = "NOW_SHOWING";
				} else {
					showingStatus = "END_OF_SHOWING";
				}
				System.out.print("Choose age rating\n(1) PG (2) PG13 (3) NC16 (4) M18 (5) R21: ");
				int ageRatingChoice = sc.nextInt();
				String ageRating;
				if (ageRatingChoice == 1) {
					ageRating = "PG";
				} else if (ageRatingChoice == 2) {
					ageRating = "PG13";
				} else if (ageRatingChoice == 3) {
					ageRating = "NC16";
				} else if (ageRatingChoice == 4) {
					ageRating = "M18";
				} else {
					ageRating = "R21";
				}
				Movie tempMovie = new Movie(movieTitle, synopis, director, cast, showingStatus, ageRating);
				movieBeans.add(tempMovie);
				break;
			case 3:
				movie.printMovies(movieBeans);
				System.out.print("Enter movie entry to delete: ");
				int moviePos = sc.nextInt();
				movieBeans.remove(moviePos);
				break;
			case 4:
				movie.printMovies(movieBeans);
				System.out.print("Enter movie entry to update: ");
				int moviePosition = sc.nextInt();
				Movie editMovie = movieBeans.get(moviePosition);
				System.out.print(
						"Selection option\n(1) Edit Title (2) Edit Synopis (3) Edit Director (4) Edit Cast (5) Edit show status (6) Edit Age rating (7) Exit: ");
				int editChoice = sc.nextInt();
				sc.nextLine();
				switch (editChoice) {
				case 1:
					System.out.println("Enter new movie title");
					String newMovieTitle = sc.nextLine();
					editMovie.setMovieTitle(newMovieTitle);
					break;
				case 2:
					System.out.println("Enter new synopis");
					String newSynopis = sc.nextLine();
					editMovie.setSynopis(newSynopis);
					break;
				case 3:
					System.out.println("Enter new director");
					String newDirector = sc.nextLine();
					editMovie.setDirector(newDirector);
					break;
				case 4:
					System.out.println("Select option\n(1) Add new cast member (2) Remove cast member");
					int castChoice = sc.nextInt();
					sc.nextLine();
					if (castChoice == 1) {
						System.out.print("Enter case name to add: ");
						String newCastName = sc.nextLine();
						editMovie.addCastMember(newCastName);
					} else {
						System.out.println("Current list of cast members");
						for (int i = 0; i < editMovie.getCast().size(); i++) {
							System.out.println(Integer.toString(i) + ". " + editMovie.getCast().get(i));
						}
						System.out.print("Enter cast number to remove: ");
						int removeCast = sc.nextInt();
						editMovie.removeCastMember(removeCast);
					}

					break;
				case 5:
					System.out.print(
							"Choose showingStatus\n(1) Coming Soon (2) Preview (3) Now Showing (4) End Of Showing: ");
					int newShowStatus = sc.nextInt();
					if (newShowStatus == 1) {
						editMovie.setShowingStatus("COMING_SOON");
					} else if (newShowStatus == 2) {
						editMovie.setShowingStatus("PREVIEW");
					} else if (newShowStatus == 3) {
						editMovie.setShowingStatus("NOW_SHOWING");
					} else {
						editMovie.setShowingStatus("END_OF_SHOWING");
					}
					break;
				case 6:
					System.out.print("Choose age rating\n(1) PG (2) PG13 (3) NC16 (4) M18 (5) R21: ");
					int newAgeRating = sc.nextInt();
					if (newAgeRating == 1) {
						editMovie.setAgeRating("PG");
					} else if (newAgeRating == 2) {
						editMovie.setAgeRating("PG13");
					} else if (newAgeRating == 3) {
						editMovie.setAgeRating("NC16");
					} else if (newAgeRating == 4) {
						editMovie.setAgeRating("M18");
					} else {
						editMovie.setAgeRating("R21");
					}
					break;
				default:
					break;
				}
			default:
				break;
			}
		} while (option > 0 && option < 4);
	}

	public void MovieMenu() throws IllegalStateException, FileNotFoundException {
		String cinemaFileName = "C:\\Users\\hue\\Desktop\\database\\cinema\\cinema.csv";
		String movieFileName = "C:\\Users\\hue\\Desktop\\database\\movie\\movie.csv";
		Cinema cinema = new Cinema();
		Movie movie = new Movie();
		List<Cinema> cinemaBeans = cinema.getCinemaList(cinemaFileName);
		List<Movie> movieBeans = movie.getMovieList(movieFileName);
		System.out.print("Selection option\n(1) Access movie database (2) Access cineplex database: ");
		if (sc.nextInt() == 1) {
			MovieDatabaseMenu(movieBeans, movie);
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
				menuAddMovieListing(movieBeans, movie, mc, hall);
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
