package boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.CinemaPrinter;
import control.ReadCSVFiles;
import control.SortTop;
import control.WriteCSVFiles;
import entity.Cinema;
import entity.Movie;

/**
 * Contains the admin user interface to allow them to modify Movie Database and
 * Cineplex Database
 * 
 * @author SS4 Group 4
 *
 */
public class AdminControl {
	Scanner sc = new Scanner(System.in);

	/**
	 * Main Menu console user interface for admin users. Admin can edit the movie
	 * database or cineplex database from here.
	 * 
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * 
	 *
	 */
	public void MainMenu()
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
				CineplexMenu(movieControl, cinemaBeans, movieBeans);
				break;
			default:
				break;
			}
		} while (c > 0 && c < 3);

	}

	public void sortPopularMovie(List<Movie> movieBeans) {
		SortTop st = new SortTop(movieBeans);
	}

	/**
	 * Console menu for admin users to edit the Cineplex database. Here they
	 * add/update/delete movie showtimes for each location.
	 * 
	 *
	 */
	public void CineplexMenu(MovieControl movieControl, List<Cinema> cinemaBeans, List<Movie> movieBeans)
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

	/**
	 * Console menu for admin users to edit the movie database. Here they can
	 * add/update/delete movies.
	 * 
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * 
	 *
	 */
	public void MovieDatabaseMenu(List<Movie> movieBeans, MovieControl movieControl)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		int option;
		do {
			System.out.print(
					"Selection option\n(1) List all movies (2) Add movie (3) Delete movie (4) Update Movie (5) Save changes and exit: ");
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
		WriteCSVFiles.movieToCSV(movieBeans);
	}
}
