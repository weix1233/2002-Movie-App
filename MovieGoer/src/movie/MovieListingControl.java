package movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.MovieListing.dayOfWeek;
import movie.MovieListing.screenType;

public class MovieListingControl implements MovieListingInterface {
	// list of movie listing = new sth;
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

	@Override
	public MovieListing addMovieListing(MovieListing listing) {

		return null;
	}

	@Override
	public void removeMovieListing(MovieListing listing) {
		// TODO Auto-generated method stub

	}

}
