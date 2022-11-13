package boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.MLDOControl;
import control.ReadCSVFiles;
import control.SortTop;
import control.WriteCSVFiles;
import entity.Cinema;
import entity.Hall;
import entity.MLDataObject;
import entity.Movie;
import entity.MovieListing;
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
	 * Location of the cinema based on ID
	 */
	private int locID;
	/**
	 * The guest/member that is currently logged in
	 */
	private User user;
	/**
	 * Creates a customerApp and lets the user choose the cinema
	 * @param user Current user
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public CustomerApp(User user) throws IllegalStateException, FileNotFoundException {
		this.currentCinema = chooseCinema();
		this.user = user;
	}
	/**
	 * Allows the customer to choose the Cinema
	 * @return Customer's choice of cinema
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public Cinema chooseCinema() throws IllegalStateException, FileNotFoundException {
		List<Cinema> cinemaBeans = ReadCSVFiles.getCinemaList();
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		if(locationID < 1 && locationID > 3) locationID = 1;
		cinemaBeans.get(locationID).setMovieListing(locationID);
		this.locID = locationID;
		return cinemaBeans.get(locationID);
	}
	/**
	 * Displays UI for customers to choose the desired function
	 * And navigates the UI for them
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws CsvRequiredFieldEmptyException 
	 * @throws CsvDataTypeMismatchException 
	 */
	public void displayMenu() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalStateException, IOException {
		int count = (50 - currentCinema.getName().length() - 9)/2;
		int choice = 0;
		String buf = "";
		MovieListing custChoice = null;
		
		for(int i = 0;i < count;i++) buf = buf + "=";
		do {
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
		choice = sc.nextInt();
		user = ReadCSVFiles.findUser(ReadCSVFiles.getLoginDetail(), user);
		switch(choice) {
			case 1:
				custChoice = this.displayMovieList();
				break;
			case 2:
				this.movieDetails(custChoice);
				break;
			case 3:
				this.bookMovie(custChoice,user);
				List<MLDataObject> MLDOBeans = MLDOControl.convertToMLDO(this.currentCinema.getMovieListing());
				WriteCSVFiles.MLDOToCSV(MLDOBeans,locID);
				break;
			case 4:
				this.bookingHistory();
				break;
			case 5:
				this.sortPopularMovie(getUniqueMovies());
				break;
			}
		} while(choice > 0 && choice < 6);
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
	public void bookMovie(MovieListing ml,User u) throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException{
		while(ml == null) {
			ml = this.displayMovieList();
		}
		Hall h = currentCinema.getHalls().get(ml.getHallID());
		Booking b = new Booking(h,ml,u);
		b.displayBooking();
	}
	/**
	 * Displays the chosen movie details
	 * If a movie has not been picked, displayMovieList() will be called
	 * Allows user to leave their reviews
	 * @param ml MovieListing chosen by customer
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws CsvRequiredFieldEmptyException 
	 * @throws CsvDataTypeMismatchException 
	 */
	public void movieDetails(MovieListing ml) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalStateException, IOException {
		while(ml == null) {
			ml = this.displayMovieList();
		}
		Movie mov = ml.getMovie();
		System.out.printf("Title: %s\nShowing Status: %s\nDirector: %s\nCast: %s\nRating: %.2f\nSypnosis: %s\nisPlatnium: %s\n",
				mov.getMovieTitle(),
				mov.getShowingStatus(),
				mov.getDirector(),
				mov.getCast(),
				mov.getOverallRating(),
				mov.getSynopis(),
				currentCinema.getHalls().get(ml.getHallID()).getIP());
		System.out.println("See past reviews? Y/N");
		String choice;
		choice = sc.next();
		switch(choice) {
		case "Y":
			int i = 0;
			while(choice.equals("Y")) {
			System.out.printf("%s Rating: %d\n",mov.getReviews().get(i).getReview(),mov.getReviews().get(i).getRating());
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
		MovieControl mc = new MovieControl();
		mc.updateReview(mov,this.getUniqueMovies());
	}
	/**
	 * Prompts user to input their credentials to check their booking history
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public void bookingHistory() throws IllegalStateException, FileNotFoundException {
		System.out.println(user.getbookingHistory());
	}
	/**
	 * Sorts the movies by either their sales or their rating
	 * Only displays the unique movies of that cinema
	 * @param movieBeans List of the unique movies
	 * @throws FileNotFoundException 
	 * @throws IllegalStateException 
	 */
	public void sortPopularMovie(List<Movie> movieBeans) throws IllegalStateException, FileNotFoundException {
		SortTop st = new SortTop(movieBeans);
		int sys = ReadCSVFiles.getOptions().get(0).getOption();
		if (sys == 3){
			int choice = 0;
			System.out.println("Please choose to sort by -- (1) Ratings (2) Ticket Sales");
			choice = sc.nextInt();
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
		else if (sys == 2) {
			st.sortBySales();
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
		List<String> s = new ArrayList<String>();
		for(int i = 0;i < ml.size();i++) {
			if(!s.contains(ml.get(i).getMovie().getMovieTitle())) {
				m.add(ml.get(i).getMovie());
				s.add(ml.get(i).getMovie().getMovieTitle());
			}
		}
		return m;
	}
}
