package boundary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
import entity.Review;
import entity.User;
/**
 * Contains the functions that interact with the customer
 * @author SS4 Group 4
 *
 */
public class CustomerApp {
	/**
	 * Scanner object for the class
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * Cinema chosen by the customer
	 */
	private Cinema currentCinema;
	/**
	 * Location ID of the cinema
	 */
	private int locID;
	/**
	 * Creates a customerApp and lets the user choose the cinema
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public CustomerApp() throws IllegalStateException, FileNotFoundException {
		this.currentCinema = chooseCinema();
	}
	/**
	 * Allows the customer to choose the Cinema
	 * @return Customer's choice of cinema
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public Cinema chooseCinema() throws IllegalStateException, FileNotFoundException {
		
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
	/**
	 * Displays UI for customers to choose the desired function
	 */
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
	/*
	 * Displays the list of movies and lets the customer choose the movie displayed
	 */
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
	/**
	 * 	 * Calls displayBooking() to let customer book their movie of choice
	 * If a movie has not been picked, displayMovieList() will be called
	 * 
	 * @param ml MovieListing chosen by customer
	 * @throws IllegalStateException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
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
	/**
	 * Displays the chosen movie details
	 * If a movie has not been picked, displayMovieList() will be called
	 * @param ml MovieListing chosen by customer
	 */
	public void movieDetails(MovieListing ml) {
		while(ml == null) {
			ml = this.displayMovieList();
		}
		Movie mov = ml.getMovie();
		System.out.printf("Title: %s\nShowing Status: %s\nDirector: %s\nCast: %s\nRating: %.2f\nSypnosis: %s\n",
				mov.getMovieTitle(),
				mov.getShowingStatus(),
				mov.getDirector(),
				mov.getCast(),
				mov.getOverallRating(),
				mov.getSynopis());
		System.out.println("See past reviews? Y/N");
		String choice;
		choice = sc.next();
		switch(choice) {
		case "Y":
			int i = 0;
			while(choice.equals("Y")) {
			System.out.printf("%s %d\n",mov.getReviews().get(i).getReview(),mov.getReviews().get(i).getRating());
			i++;
			if(i == mov.getReviews().size()) {
				System.out.println("End of reviews");
				break;
			}
			System.out.println("Another review? Y/N");
			choice = sc.next();
			}
			break;
		default:
			break;
		}
		System.out.println("Leave a review for this movie? Y/N");
		choice = sc.next();
		switch(choice) {
		case "Y":
			System.out.println("Enter your name:");
			String name = sc.next();
			System.out.println("Type your review:");
			String essay = sc.next();
			System.out.println("Give your rating (1 ~ 5):");
			int rate = sc.nextInt();
			if (rate > 5) rate = 5;
			if (rate < 1) rate = 1;
			Review r = new Review(essay,rate,name);
		}
	}
	/**
	 * Prompts user to input their credentials to check their booking history
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Sorts the movies by either their sales or their rating
	 * Only displays the unique movies of that cinema
	 * @param movieBeans List of the unique movies
	 * @param sys System option chosen by admins
	 */
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
	/**
	 * Gets the unique movies within the movieListing
	 * @return Unique movies within movieListing
	 */
	public List<Movie> getUniqueMovies(){
		List<MovieListing> ml = this.currentCinema.getMovieListing();
		List<Movie> m = new ArrayList<Movie>();
		for(int i = 0;i < ml.size();i++) {
			if(!m.contains(ml.get(i).getMovie())) {
				m.add(ml.get(i).getMovie());
			}
		}
		return m;
	}
}
