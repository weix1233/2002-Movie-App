package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.CinemaPrinter;
import control.ReadCSVFiles;
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
	/**
	 * Scanner object for the class
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * Main Menu console user interface for admin users. Admin can edit the movie
	 * database or cineplex database from here.
	 * 
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * @throws NoSuchAlgorithmException
	 * 
	 *
	 */
	public void MainMenu() throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException,
			IOException, NoSuchAlgorithmException {
		MovieControl movieControl = new MovieControl();
		List<Cinema> cinemaBeans = ReadCSVFiles.getCinemaList();
		List<Movie> movieBeans = ReadCSVFiles.getMovieList();
		int c;
		do {
			System.out.print("Select option\n(1) Access movie database (2) Access cineplex database (3) Access Options (4) Exit: ");
			c = sc.nextInt();
			switch (c) {
			case 1:
				MovieDatabaseMenu(movieBeans, movieControl);
				break;
			case 2:
				CineplexMenu(movieControl, cinemaBeans, movieBeans);
				break;
			case 3:
				AdminOptions ao = new AdminOptions();
				ao.displayOptions();
				break;
			default:
				break;
			}
		} while (c > 0 && c < 4);
		ControlPanel.main(null);
	}

	/**
	 * Console menu for admin users to edit the Cineplex database. Here they
	 * add/update/delete movie showtimes for each location.
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * 
	 *
	 */
	public void CineplexMenu(MovieControl movieControl, List<Cinema> cinemaBeans, List<Movie> movieBeans)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException,
			NoSuchAlgorithmException, IOException {
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
				String options = movieControl.printCurrentMovieList(movieBeans);
				currentCinema.addMovieListing(movieBeans, options);
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
		System.out.println("---- Exited ----");
		MainMenu();
	}

	/**
	 * Console menu for admin users to edit the movie database. Here they can
	 * add/update/delete movies.
	 * 
	 * @param movieBeans List of movies
	 * @param movieControl movieControl object
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * @throws NoSuchAlgorithmException
	 * 
	 *
	 */
	public void MovieDatabaseMenu(List<Movie> movieBeans, MovieControl movieControl) throws IllegalStateException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, NoSuchAlgorithmException {
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
		System.out.println("---- Exited ----");
		MainMenu();
	}
}
