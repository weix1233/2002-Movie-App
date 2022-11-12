package boundary;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import control.CinemaControl;
import control.SortTop;
import entity.Cinema;
import entity.Hall;
import entity.Movie;

public class AdminControl {
	Scanner sc = new Scanner(System.in);

	public void MainMenu() throws IllegalStateException, FileNotFoundException {
		String cinemaFileName = "C:\\Users\\tanju\\git\\2002-Movie-App2424424\\MovieGoer\\database\\cinema\\cinema.csv";
		String movieFileName = "C:\\Users\\tanju\\git\\2002-Movie-App2424424\\MovieGoer\\database\\movie\\movie.csv";
		MovieControl movieControl = new MovieControl();
		CinemaControl cinemaControl = new CinemaControl();
		List<Cinema> cinemaBeans = cinemaControl.getCinemaList(cinemaFileName);
		List<Movie> movieBeans = movieControl.getMovieList(movieFileName);
		int c;
		do {
			System.out.print("Select option\n(1) Access movie database (2) Access cineplex database: ");
			c = sc.nextInt();
			switch (c) {
			case 1:
				MovieDatabaseMenu(movieBeans, movieControl);
				break;
			case 2:
				MovieMenu(cinemaControl, movieControl, cinemaBeans, movieBeans);
				break;
			default:
				break;
			}
		} while (c > 0 && c < 3);

	}

	public void sortPopularMovie(List<Movie> movieBeans) {
		SortTop st = new SortTop(movieBeans);
	}

	public void MovieMenu(CinemaControl cinemaControl, MovieControl movieControl, List<Cinema> cinemaBeans,
			List<Movie> movieBeans) throws IllegalStateException, FileNotFoundException {
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		cinemaControl.getCinemaInfo(cinemaBeans, locationID);
		System.out.print("Select cinema hall number (1 ~ 3): ");
		int hallID = sc.nextInt();
		Hall hall = cinemaBeans.get(locationID).getHall(hallID);

		int option;
		do {
			System.out.print(
					"Select option\n(1) Add movie showtime (2) Remove movie showtime (3) Update movie showtime (4) List current movie showtimes (5) Exit: ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				movieControl.printCurrentMovieList(movieBeans);
				hall.hallAddMovieListing(movieBeans);
				break;
			case 2:
				hall.hallDelMovieListing();
				break;
			case 3:
				hall.hallUpdateMovieListing();
				break;
			case 4:
				hall.hallListAllMovieListing();
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
