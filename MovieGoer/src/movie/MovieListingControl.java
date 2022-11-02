package movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.MovieListing.ageRating;
import movie.MovieListing.dayOfWeek;
import movie.MovieListing.screenType;
import movie.MovieListing.showingStatus;

public class MovieListingControl implements MovieListingInterface {
	Scanner sc = new Scanner(System.in);
	String movieFilePath = "C:\\Users\\tanju\\Desktop\\movie\\movie.csv";

	@Override
	public List<Movie> listMovieTitles() throws IllegalStateException, FileNotFoundException {
		// TODO Auto-generated method stub
		List<Movie> beans = new CsvToBeanBuilder(new FileReader(movieFilePath)).withType(Movie.class).build().parse();
		System.out.println("Movies collection in database");
		for (int i = 1; i <= beans.size(); i++) {
			System.out.println(Integer.toString(i) + ". " + beans.get(i - 1).getMovieTitle());
		}
		return beans;
	}

	public screenType chooseScreenType() {
		System.out.print("Choose screenType\n(1) Two-D screen (2) Three-D screen: ");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			return screenType.TWO_D;
		case 2:
			return screenType.THREE_D;
		default:
			System.out.println("Error in choice, defaulting to 2D");
			return screenType.TWO_D;
		}
	}

	public showingStatus chooseShowingStatus() {
		System.out.print("Choose showing status\n(1) COMING_SOON (2) PREVIEW (3) NOW_SHOWING (4) END_OF_SHOWING: ");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			return showingStatus.COMING_SOON;
		case 2:
			return showingStatus.PREVIEW;
		case 3:
			return showingStatus.NOW_SHOWING;
		case 4:
			return showingStatus.END_OF_SHOWING;
		default:
			System.out.println("Error in choice, defaulting to COMING_SOON");
			return showingStatus.COMING_SOON;
		}
	}

	public ageRating chooseAgeRating() {
		System.out.print("Choose age rating\n(1) PG (2) PG13 (3) NC16 (4) M18 (5) R21: ");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			return ageRating.PG;
		case 2:
			return ageRating.PG13;
		case 3:
			return ageRating.NC16;
		case 4:
			return ageRating.M18;
		case 5:
			return ageRating.R21;
		default:
			System.out.println("Error input, temporarily assigned PG");
			return ageRating.PG;
		}
	}

	public int chooseCinemaHall() {
		System.out.print("Choose cinema hall: 1 ~ 3: ");
		return sc.nextInt();
	}

	public int chooseShowTime() {
		System.out.print("Enter showtime in 24 hour format, e.g: 1230: ");
		return sc.nextInt();
	}

	public dayOfWeek chooseDay() {
		System.out.print(
				"Choose day of showing\n(1) Monday (2) Tuesday (3) Wednesday (4) Thursday (5) Friday (6) Saturday (7) Sunday: ");
		int c = sc.nextInt();
		dayOfWeek day;
		switch (c) {
		case 1:
			return dayOfWeek.MON;
		case 2:
			return dayOfWeek.TUES;
		case 3:
			return dayOfWeek.WED;
		case 4:
			return dayOfWeek.THURS;
		case 5:
			return dayOfWeek.FRI;
		case 6:
			return dayOfWeek.SAT;
		case 7:
			return dayOfWeek.SUN;
		default:
			System.out.println("Error in choice, defaulting to Monday");
			return dayOfWeek.MON;
		}
	}

	public MovieListing createMovieListing(List<Movie> beans, int lengthOfList) {
		System.out.print("\nEnter the number of movie to create: ");
		int pos = sc.nextInt();
		int c = 0;
		Movie mv = beans.get(pos);
		screenType st = chooseScreenType();
		showingStatus ss = chooseShowingStatus();
		ageRating age = chooseAgeRating();
		int cinemaHall = chooseCinemaHall();
		int showtime = chooseShowTime();
		dayOfWeek day = chooseDay();
		MovieListing ml = new MovieListing(lengthOfList, mv, st, ss, age, day, showtime, cinemaHall);
		return ml;
	}
	// PG, PG13, NC16, M18, R21

	@Override
	public MovieListing addMovieListing(MovieListing listing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeMovieListing(MovieListing listing) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMovieListing(List<MovieListing> beans, int movieListingID) {
		// TODO Auto-generated method stub
		MovieListing ml = beans.get(movieListingID);
		System.out.println("Current settings");
		ml.printListing();
		int choice = 0;
		while (true) {
			System.out.print(
					"Enter setting to change\n(1) Screen Type (2) Showing Status (3) Age Rating (4) Cinema Hall (5) Show Time (6) Day of Listing (7) Exit: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				ml.setType(chooseScreenType());
				break;
			case 2:
				ml.setStatus(chooseShowingStatus());
				break;
			case 3:
				ml.setAgeRate(chooseAgeRating());
				break;
			case 4:
				ml.setCinemaHall(chooseCinemaHall());
				break;
			case 5:
				ml.setShowtime(chooseShowTime());
				break;
			case 6:
				ml.setDay(chooseDay());
				break;
			case 7:
				return;
			default:
				return;
			}
			System.out.println("Updated settings");
			ml.printListing();
		}
	}

}
