package boundary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import entity.Movie;

public class MovieControl {
	Scanner sc = new Scanner(System.in);

	public List<Movie> getMovieList(String path) throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(path)).withType(Movie.class).build().parse();
	}

	public void printMovies(List<Movie> beans) {
		for (int i = 0; i < beans.size(); i++) {
			System.out.printf(
					"%s. Title: %s | Synopis: %s | Director: %s | Cast: %s | ShowingStatus: %s | Age Rating: %s \n",
					Integer.toString(i), beans.get(i).getMovieTitle(), beans.get(i).getSynopis(),
					beans.get(i).getDirector(), beans.get(i).getCast(), beans.get(i).getShowingStatus(),
					beans.get(i).getAgeRating());
		}
	}

	public void printCurrentMovieList(List<Movie> beans) {
		for (int i = 0; i < beans.size(); i++) {
			if (beans.get(i).getShowingStatus().equals("NOW_SHOWING")) {
				System.out.println(i + ". Title: " + beans.get(i).getMovieTitle() + " | Age Rating: "
						+ beans.get(i).getAgeRating());
			}
		}
	}

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

	public void delMovieFromDatabase(List<Movie> movieBeans) {
		System.out.print("Enter movie entry to delete: ");
		int moviePos = sc.nextInt();
		movieBeans.remove(moviePos);
	}

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
						editMovie.removeCastMember(updateDelCast(editMovie));
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

	public String chooseMovieTitle() {
		System.out.print("Enter movie title: ");
		return sc.nextLine();
	}

	public String chooseSynopis() {
		System.out.print("Enter synopis title: ");
		return sc.nextLine();
	}

	public String chooseDirector() {
		System.out.print("Enter director: ");
		return sc.nextLine();
	}

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

	public String updateAddCast() {
		System.out.print("Enter cast name to add: ");
		return sc.nextLine();
	}

	public int updateDelCast(Movie editMovie) {
		System.out.println("Current list of cast members");
		for (int i = 0; i < editMovie.getCast().size(); i++) {
			System.out.println(Integer.toString(i) + ". " + editMovie.getCast().get(i));
		}
		System.out.print("Enter cast number to remove: ");
		return sc.nextInt();
	}

}