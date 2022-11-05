package movie;

import java.util.Scanner;

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
	private String showtime;
	private int hallID;
	private Review[] reviews;

	public MovieListing(int movieListingID, Movie movie, screenType type, dayOfWeek day, String showtime, int hallID) {
		this.movieListingID = movieListingID;
		this.movie = movie;
		this.type = type;
		this.day = day;
		this.showtime = showtime;
		this.hallID = hallID;
		Review[] reviews = new Review[1];
	}

	public int getMovieListingID() {
		return movieListingID;
	}

	public void printListing() {
		System.out.println(Integer.toString(movieListingID) + " " + movie.getMovieTitle() + " " + type.name() + " "
				+ movie.getShowingStatus() + " " + movie.getAgeRating() + " " + day.name() + " "
				+ showtime + " " + Integer.toString(hallID));
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

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public int getHallID() {
		return hallID;
	}

	public void setCinemaHall(int hallID) {
		this.hallID = hallID;
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
				else { 
					//first two rows for couple seats
					if(i==0 || i==1) {
						{
							if((j<6 && j%2==0) || (j>6 && j%2!=0)) {
								System.out.printf(ConsoleColors.PURPLE_BACKGROUND_BRIGHT + "[ || ]" + ConsoleColors.RESET);
							}
						}
					}
					//last two rows for elite seats
					if(i==7 || i==8) {
						System.out.printf(ConsoleColors.YELLOW_BACKGROUND + "%s" + ConsoleColors.RESET, seat[i-1][j-1].seatSlot()); 
					}
					else {
						System.out.printf("%s", seat[i-1][j-1].seatSlot()); 
					}
				}
			}
			System.out.printf("\n");
		}
		System.out.println("=================Entrance================");	
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
