package admin;
 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.*;

public class CustomerApp {
	private Cinema currentCinema;
	public CustomerApp() throws IllegalStateException, FileNotFoundException {
		this.currentCinema = chooseCinema();
	}
	public Cinema chooseCinema() throws IllegalStateException, FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		String cinemaFileName = "C:\\Users\\Valen\\git\\2002-Movie-App-branch\\MovieGoer\\database\\cinema\\cinema.csv";
		List<Cinema> cinemaBeans = new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build()
				.parse();
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		return cinemaBeans.get(locationID-1);
	}
	public void BookMovie(MovieListing ml) throws IllegalStateException, FileNotFoundException {
		//showMovies(currentCinema.getML());
		Booking b = new Booking(ml,currentCinema);
		//b = b.displayBooking();
		
	}
	public void displayMenu() {
		String buf = "";
		int count = (50 - this.currentCinema.getName().length() - 9)/2;
		for(int i = 0;i < count;i++) buf = buf + "=";
		System.out.println("==================================================");
		//System.out.printf("%d",count);
		if(currentCinema.getName().length() % 2 == 0) System.out.print("=");
		System.out.printf("%s Cathay %s %s\n",buf,currentCinema.getName(),buf);
		System.out.println("==================================================");
		
	}
	public static void showMovies(List<MovieListing> mListings) {
		//displayUniqueMovie();
	}
}
