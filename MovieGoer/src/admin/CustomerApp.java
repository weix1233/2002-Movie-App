package admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.Booking;
import movie.Cinema;
import movie.Hall;
import movie.MovieListing;

public class CustomerApp {
	private Cinema currentCinema;
	public CustomerApp() throws IllegalStateException, FileNotFoundException {
		this.currentCinema = chooseCinema();
		}
	public Cinema chooseCinema() throws IllegalStateException, FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		String cinemaFileName = "C:\\Users\\Valen\\git\\2002-Movie-App-branch\\MovieGoer\\database\\cinema\\cinema.csv";
		List<Cinema> cinemaBeans = new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build()
				.parse();
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		return cinemaBeans.get(locationID);
	}
	public void displayMenu() {
		int count = (50 - currentCinema.getName().length() - 9)/2;
		String buf = "";
		for(int i = 0;i < count;i++) buf = buf + "=";
		System.out.println("==================================================");
		if(count % 2 == 0) System.out.print("=");
		System.out.printf("%s Cathay %s %s",buf,currentCinema.getName(),buf);
		System.out.println("====== 1. List Movie                        ======");
		System.out.println("====== 2. View Movie Details                ======");
		System.out.println("====== 3. Check Seats                       ======");
		System.out.println("====== 4. View Booking history              ======");
		System.out.println("====== 5. View Top 5 Movies                 ======");
		System.out.println("==================================================");
	}
	public void bookMovie(int hallID,MovieListing ml){
		Hall h = currentCinema.getHall(hallID);
		Booking b = new Booking(h,currentCinema,ml);
		b.displayBooking();
	}
	public void bookingHistory() {
		//If user exist, show...
		
		//else, reprompt the user to reenter credentials.
	}
}
