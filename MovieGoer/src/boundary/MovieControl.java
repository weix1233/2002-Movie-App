package boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.WriteCSVFiles;
import entity.Movie;
import entity.Review;

/**
 * Contains the functions to modify the Movie database (csv file)
 * 
 * @author SS4 Group 4
 *
 */
public class MovieControl {
	Scanner sc = new Scanner(System.in);

	/**
	 * Prints all movies information currently in database
	 * 
	 * @param movieBeans List of Movie objects extracted from database (csv file)
	 */
	public void printMovies(List<Movie> movieBeans) {
		for (int i = 0; i < movieBeans.size(); i++) {
			System.out.printf(
					"%s. Title: %s | Synopis: %s | Director: %s | Cast: %s | ShowingStatus: %s | Age Rating: %s \n",
					Integer.toString(i), movieBeans.get(i).getMovieTitle(), movieBeans.get(i).getSynopis(),
					movieBeans.get(i).getDirector(), movieBeans.get(i).getCast(), movieBeans.get(i).getShowingStatus(),
					movieBeans.get(i).getAgeRating());
		}
	}

	/**
	 * Filter and print movie information that are currently NOW_SHOWING in cinemas.
	 * 
	 * @param movieBeans
	 */
	public String printCurrentMovieList(List<Movie> movieBeans) {
		String options = "";
		for (int i = 0; i < movieBeans.size(); i++) {
			if (movieBeans.get(i).getShowingStatus().equals("NOW_SHOWING")) {
				options += Integer.toString(i) + " ";
				System.out.printf("%s. Title: %s | Synopis: %s | Director: %s | Cast: %s | Age Rating: %s\n",
						Integer.toString(i), movieBeans.get(i).getMovieTitle(), movieBeans.get(i).getSynopis(),
						movieBeans.get(i).getDirector(), movieBeans.get(i).getCast(), movieBeans.get(i).getAgeRating());
			}
		}
		return options;
	}

	/**
	 * Add a new movie to the list of Movie objects
	 * 
	 * @param movieBeans List of Movie objects extracted from database (csv file)
	 */
	public void addMovieToDatabase(List<Movie> movieBeans) {
		String movieTitle = chooseMovieTitle();
		String synopis = chooseSynopis();
		String director = chooseDirector();
		List<String> cast = chooseCast();
		String showingStatus = chooseShowingStatus();
		String ageRating = chooseAgeRating();
		Movie tempMovie = new Movie(movieTitle, synopis, director, cast, showingStatus, ageRating);
		movieBeans.add(tempMovie);
	}

	/**
	 * Deletes a movie object from the list of Movie objects by its index position.
	 * 
	 * @param movieBeans List of Movie objects extracted from database (csv file)
	 */

	public void delMovieFromDatabase(List<Movie> movieBeans) {
		System.out.print("Enter movie entry to delete: ");
		int moviePos = sc.nextInt();
		movieBeans.remove(moviePos);
	}

	/**
	 * Updates a movie object information from the list of Movie objects by its
	 * index position.
	 * 
	 * @param movieBeans List of Movie objects extracted from database (csv file)
	 */
	public void updateMovieInDatabase(List<Movie> movieBeans) {
		System.out.print("Enter movie entry to update: ");
		int moviePosition = sc.nextInt();
		Movie editMovie = movieBeans.get(moviePosition);
		int editChoice;
		do {
			System.out.print(
					"Select option\n(1) Edit Title (2) Edit Synopis (3) Edit Director (4) Edit Cast (5) Edit show status (6) Edit Age rating (7) Exit: ");
			editChoice = sc.nextInt();
			switch (editChoice) {
			case 1:
				sc.nextLine();
				editMovie.setMovieTitle(chooseMovieTitle());
				break;
			case 2:
				sc.nextLine();
				editMovie.setSynopis(chooseSynopis());
				break;
			case 3:
				sc.nextLine();
				editMovie.setDirector(chooseDirector());
				break;
			case 4:
				sc.nextLine();
				int castChoice;
				do {
					System.out.println("Select option\n(1) Add new cast member (2) Remove cast member (3) Exit");
					castChoice = sc.nextInt();
					sc.nextLine();
					if (castChoice == 1) {
						editMovie.addCastMember(updateAddCast());
					} else if (castChoice == 2) {
						int castPosition = updateDelCast(editMovie);
						if (castPosition == -1) {
							break;
						}
						editMovie.removeCastMember(castPosition);
					}
				} while (castChoice > 0 && castChoice < 3);
				break;
			case 5:
				sc.nextLine();
				editMovie.setShowingStatus(chooseShowingStatus());
				break;
			case 6:
				sc.nextLine();
				editMovie.setAgeRating(chooseAgeRating());
				break;
			default:
				break;
			}
		} while (editChoice > 0 && editChoice < 7);

	}

	/**
	 * Console interface to prompt user for a movie title
	 * 
	 * @return Movie title entered by user
	 */
	public String chooseMovieTitle() {
		System.out.print("Enter movie title: ");
		return sc.nextLine();
	}

	/**
	 * Console interface to prompt user for the movie's synopis
	 * 
	 * @return Synopis entered by user
	 */
	public String chooseSynopis() {
		System.out.print("Enter synopis title: ");
		return sc.nextLine();
	}

	/**
	 * Console interface to prompt user for the movie's director name
	 * 
	 * @return Director name entered by user
	 */
	public String chooseDirector() {
		System.out.print("Enter director: ");
		return sc.nextLine();
	}

	/**
	 * Console interface to prompt user to enter the cast's name to add. Each cast
	 * will be added to a temporary String list
	 * 
	 * @return List of cast added by user
	 */
	public List<String> chooseCast() {
		List<String> cast = new ArrayList<String>();
		while (true) {
			System.out.print("Enter cast name or type 'q' to quit: ");
			String userInput = sc.nextLine();
			if (userInput.equals("q")) {
				break;
			}
			cast.add(userInput);
		}
		return cast;
	}

	/**
	 * Console interface to prompt user to select the showing status of a movie. (1)
	 * Coming soon (2) Preview (3) Now Showing (4) End Of Showing
	 * 
	 * @return showing status of a movie
	 */
	public String chooseShowingStatus() {
		System.out.print("Choose showing status\n(1) Coming Soon (2) Preview (3) Now Showing (4) End Of Showing: ");
		int showingStatusChoice = sc.nextInt();
		if (showingStatusChoice == 1) {
			return "COMING_SOON";
		} else if (showingStatusChoice == 2) {
			return "PREVIEW";
		} else if (showingStatusChoice == 3) {
			return "NOW_SHOWING";
		} else {
			return "END_OF_SHOWING";
		}
	}

	/**
	 * Console interface to prompt user to select the age rating of a movie. (1) PG
	 * (2) PG13 (3) NC16 (4) M18 (5) R21
	 * 
	 * @return age rating of a movie
	 */
	public String chooseAgeRating() {
		System.out.print("Choose age rating\n(1) PG (2) PG13 (3) NC16 (4) M18 (5) R21: ");
		int ageRatingChoice = sc.nextInt();
		if (ageRatingChoice == 1) {
			return "PG";
		} else if (ageRatingChoice == 2) {
			return "PG13";
		} else if (ageRatingChoice == 3) {
			return "NC16";
		} else if (ageRatingChoice == 4) {
			return "M18";
		} else {
			return "R21";
		}
	}

	/**
	 * Updating a movie information. Console interface to prompt user to enter a
	 * cast name to add to the current list of casts.
	 * 
	 * @return cast
	 */
	public String updateAddCast() {
		System.out.print("Enter cast name to add: ");
		return sc.nextLine();
	}

	/**
	 * Updating a movie information. Console interface to prompt user to choose
	 * which cast to remove from current list of casts
	 * 
	 * @param editMovie
	 * @return
	 */
	public int updateDelCast(Movie editMovie) {
		System.out.println("Current list of cast members");
		if (editMovie.getCast().size() <= 1) {
			System.out.println("---- Error! Unable to remove any more cast member in the movie ----");
			return -1;
		}
		for (int i = 0; i < editMovie.getCast().size(); i++) {
			System.out.println(Integer.toString(i) + ". " + editMovie.getCast().get(i));
		}
		System.out.print("Enter cast number to remove: ");
		return sc.nextInt();
	}

	/**
	 * Console interface for a user to leave a review on the selected movie.
	 * 
	 * @param mov        the Movie selected to leave a review on
	 * @param path       filepath to movie database to write changes to the movie
	 *                   database (csv file)
	 * @param movieBeans list of Movie objects extracted from the movie database
	 *                   (csv file)
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void updateReview(Movie mov, List<Movie> movieBeans)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalStateException, IOException {
		System.out.println("Leave a review for this movie? Y/N");
		String choice = sc.next();
		switch (choice) {
		case "Y":
			sc.nextLine();
			System.out.println("Enter your name:");
			String name = sc.nextLine();
			System.out.println("Type your review:");
			String essay = sc.nextLine();
			System.out.println("Give your rating (1 ~ 5):");
			int rate = sc.nextInt();
			if (rate > 5)
				rate = 5;
			if (rate < 1)
				rate = 1;
			Review r = new Review(essay, rate, name);
			List<Review> rList = mov.getReviews();
			rList.add(r);
			mov.setReviews(rList);
			WriteCSVFiles wcf = new WriteCSVFiles();
			wcf.movieToCSV(movieBeans);
			break;
		default:
			break;
		}
	}
}
