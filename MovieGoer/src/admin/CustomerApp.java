package admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.*;

public class CustomerApp {
	private Cinema currentCinema;
	public void chooseCinema() throws IllegalStateException, FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		String cinemaFileName = "C:\\Users\\Valen\\git\\2002-Movie-App-branch\\MovieGoer\\database\\cinema\\cinema.csv";
		List<Cinema> cinemaBeans = new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build()
				.parse();
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		currentCinema = cinemaBeans.get(locationID);
	}
	public void BookMovie(MovieListing ml) throws IllegalStateException, FileNotFoundException {
		showMovies(currentCinema.getML());
		Booking b = new Booking(ml,currentCinema.getCinemaID());
		b = b.displayBooking();
		
	}
	public static void showMovies(List<MovieListing> mListings) {
		displayUniqueMovie();
	}
}
