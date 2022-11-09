package entity;

public class MovieListing {

	public enum screenType {
		TWO_D, THREE_D
	};

	public enum dayOfWeek {
		MON, TUES, WED, THURS, FRI, SAT, SUN, PH
	};

	private Movie movie;
	private screenType type;
	private dayOfWeek day;
	private String showtime;
	private int hallID;
	private Review[] reviews;

	public MovieListing(Movie movie, screenType type, dayOfWeek day, String showtime, int hallID) {
		this.movie = movie;
		this.type = type;
		this.day = day;
		this.showtime = showtime;
		this.hallID = hallID;
		Review[] reviews = new Review[1];
	}

	public void printListing() {
		System.out.println("Title: " + movie.getMovieTitle() + " | Screen type: " + type.name() + " | Show time: "
				+ showtime + " | Age Rating: " + movie.getAgeRating() + " | Hall: " + this.hallID);
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

}
