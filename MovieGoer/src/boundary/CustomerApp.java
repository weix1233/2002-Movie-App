package boundary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.SortTop;
import entity.Cinema;
import entity.Hall;
import entity.Movie;
import entity.MovieListing;
import entity.User;

public class CustomerApp {
	
	private Cinema currentCinema;
	private int locID;
	
	public CustomerApp() throws IllegalStateException, FileNotFoundException {
		this.currentCinema = chooseCinema();
	}
	
	public Cinema chooseCinema() throws IllegalStateException, FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		String cinemaFileName = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\cinema.csv";
		List<Cinema> cinemaBeans = new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build()
				.parse();
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		if(locationID != 1 || locationID != 2 || locationID != 3) locationID = 1;
		this.locID = locationID;
		cinemaBeans.get(locationID).setMovieListing(locationID);
		return cinemaBeans.get(locationID);
	}
	
	public void displayMenu() {
		int count = (50 - currentCinema.getName().length() - 9)/2;
		String buf = "";
		for(int i = 0;i < count;i++) buf = buf + "=";
		System.out.println("==================================================");
		if(currentCinema.getName().length() % 2 == 0) System.out.print("=");
		System.out.printf("%s Cathay %s %s\n",buf,currentCinema.getName(),buf);
		System.out.println("====== 1. List Movie Listing                ======");
		System.out.println("====== 2. View Movie Details                ======");
		System.out.println("====== 3. Check Seats                       ======");
		System.out.println("====== 4. View Booking history              ======");
		System.out.println("====== 5. View Top 5 Movies                 ======");
		System.out.println("====== 6. Exit                              ======");
		System.out.println("==================================================");
	}
	
	public MovieListing displayMovieList() {
		List<MovieListing> ml = currentCinema.getMovieListing();
		for(int i = 1; i < ml.size(); i++) {
			System.out.printf("%d: ",i);
			ml.get(i).printListing();
		}
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice > ml.size()) return null;
		return ml.get(choice);
	}
	
	public void bookMovie(MovieListing ml) throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException{
		while(ml == null) {
			ml = this.displayMovieList();
		}
		Hall h = currentCinema.getHall(ml.getHallID());
		String filePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv";
		List<User> u = new CsvToBeanBuilder(new FileReader(filePath)).withType(User.class).build().parse();
		Booking b = new Booking(h,ml,u);
		b.displayBooking();
	}
	
	public void movieDetails(MovieListing ml) {
		while(ml == null) {
			ml = this.displayMovieList();
		}
		Movie mov = ml.getMovie();
		System.out.printf("Title: %s\nDirector: %s\nCast: %s\nRating: %.2f\nSypnosis: %s\n",
				mov.getMovieTitle(),
				mov.getDirector(),
				mov.getCast(),
				mov.getOverallRating(),
				mov.getSynopis());
	}
	
	public void bookingHistory() throws IllegalStateException, FileNotFoundException {
		boolean check = false;
		String name = null;
		String email = null;
		int mobileNo = 0;
		int attempt = 0;
		String filePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv";
		List<User> userBeans = new CsvToBeanBuilder(new FileReader(filePath)).withType(User.class).build().parse();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter your credentials");
			System.out.println("Name: ");
			name = sc.next();
			System.out.println("Email: ");
			email = sc.next();
			System.out.println("Mobile No: ");
			mobileNo = sc.nextInt();
			System.out.println("Checking ...");
			for(int i = 0;i < userBeans.size();i++) {
				if(userBeans.get(i).getName().equals(name)) {
					if(userBeans.get(i).getEmail().equals(email)) {
						if(userBeans.get(i).getMobileNo() == mobileNo) {
							System.out.println(userBeans.get(i).getbookingHistory());
							check = true;
							break;
						}
					}
				}
			}
			if(check == true) {
				break;	
			}
			System.out.printf("Check failed...\nAttempts left: %d\n",3 - ++attempt);
		} while(attempt < 3);
		System.out.println("Returning to menu...");
	}
	
	public void sortPopularMovie(List<Movie> movieBeans,int sys) {
		SortTop st = new SortTop(movieBeans);
		if (sys == 0){
			int choice = 0;
			System.out.println("Please choose to sort by -- (1) Ratings (2) Ticket Sales");
			switch(choice) {
			case 1:
				st.sortByRating();
				break;
			case 2:
				st.sortBySales();
				break;
			default:
				st.sortByRating();
				break;
			}
		}
		else if (sys == 1) {
			st.sortByRating();
		}
		else {
			st.sortBySales();
		}
		
	}
}
