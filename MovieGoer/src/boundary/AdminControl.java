package boundary;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import control.CinemaPrinter;
import control.ReadCSVFiles;
import control.SortTop;
import entity.Cinema;
import entity.Movie;

public class AdminControl {
	Scanner sc = new Scanner(System.in);

	public void MainMenu() throws IllegalStateException, FileNotFoundException {
		MovieControl movieControl = new MovieControl();
		List<Cinema> cinemaBeans = ReadCSVFiles.getCinemaList();
		List<Movie> movieBeans = ReadCSVFiles.getMovieList();
		int c;
		do {
			System.out.print("Select option\n(1) Access movie database (2) Access cineplex database: ");
			c = sc.nextInt();
			switch (c) {
			case 1:
				MovieDatabaseMenu(movieBeans, movieControl);
				break;
			case 2:
				MovieMenu(movieControl, cinemaBeans, movieBeans);
				break;
			default:
				break;
			}
		} while (c > 0 && c < 3);

	}

	public void sortPopularMovie(List<Movie> movieBeans) {
		SortTop st = new SortTop(movieBeans);
	}

	public void MovieMenu(MovieControl movieControl, List<Cinema> cinemaBeans, List<Movie> movieBeans)
			throws IllegalStateException, FileNotFoundException {
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		cinemaBeans.get(locationID).setMovieListing(locationID);
		CinemaPrinter cp = new CinemaPrinter();
		cp.getCinemaInfo(cinemaBeans, locationID);
		Cinema currentCinema = cinemaBeans.get(locationID);

		int option;
		do {
			System.out.print(
					"Select option\n(1) Add movie showtime (2) Remove movie showtime (3) Update movie showtime (4) List current movie showtimes (5) Exit: ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				movieControl.printCurrentMovieList(movieBeans);
				currentCinema.addMovieListing(movieBeans);
				break;
			case 2:
				currentCinema.delMovieListing();
				break;
			case 3:
				currentCinema.updateMovieListing();
				break;
			case 4:
				currentCinema.listMovieListing();
			default:
			}
		} while (option < 5 && option > 0);
		System.out.println("exited");
	}

	public void MovieDatabaseMenu(List<Movie> movieBeans, MovieControl movieControl)
			throws IllegalStateException, FileNotFoundException {
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
				movieControl.printMovies(movieBeans);
				movieControl.updateMovieInDatabase(movieBeans);
				break;
			default:
				break;
			}
		} while (option > 0 && option < 5);
	}
}
