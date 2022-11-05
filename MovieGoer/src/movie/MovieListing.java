package movie;

import java.util.Scanner;
import movie.Seat;

public class MovieListing {

	protected enum screenType {
		TWO_D, THREE_D
	};

	protected enum dayOfWeek {
		MON, TUES, WED, THURS, FRI, SAT, SUN, PH
	};

	private int movieListingID;
	private Movie movie;
	private screenType type;
	private dayOfWeek day;
	private int showtime;
	private int cinemaHall;
	private Seat[][] seat = new Seat[9][13];

	public MovieListing(int movieListingID, Movie movie, screenType type, dayOfWeek day, int showtime, int cinemaHall) {
		this.movieListingID = movieListingID;
		this.movie = movie;
		this.type = type;
		this.day = day;
		this.showtime = showtime;
		this.cinemaHall = cinemaHall;
	}

	public int getMovieListingID() {
		return movieListingID;
	}

	public void printListing() {
		System.out.println(Integer.toString(movieListingID) + " " + movie.getMovieTitle() + " " + type.name() + " "
				+ movie.getShowingStatus() + " " + movie.getAgeRating() + " " + day.name() + " "
				+ Integer.toString(showtime) + " " + Integer.toString(cinemaHall));
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public screenType getType() {
		return type;
	}

	public void setType(screenType type) {
		this.type = type;
	}

	public dayOfWeek getDay() {
		return day;
	}

	public void setDay(dayOfWeek day) {
		this.day = day;
	}

	public int getShowtime() {
		return showtime;
	}

	public void setShowtime(int showtime) {
		this.showtime = showtime;
	}

	public int getCinemaHall() {
		return cinemaHall;
	}

	public void setCinemaHall(int cinemaHall) {
		this.cinemaHall = cinemaHall;
	}
	
	public void showSeats(){
		char base = 'A';
		Scanner sc = new Scanner(System.in);
		System.out.println("===================Screen================");
		for(int i=0; i<9; i++) {
			char rowLetter = (char)((int)base + i);
			System.out.printf("%c ", rowLetter);
			for(int j=0; j<13; j++) {
				if(j==6) { System.out.printf("   "); }
				else { System.out.printf("%s", seat[i-1][j-1].seatSlot()); }
			}
			System.out.printf("\n");
		}
		System.out.println("=================Entrance================");	
	}
	
	public void updateSeats(int row, int col, int custId) {
		if (!seat[row-1][col-1].isOccupied()){
            seat[row-1][col-1].assign(custId);
            System.out.println("Seat assigned!");
        }
        else
            System.out.println("Seat already assigned to a customer.");
	}
}
