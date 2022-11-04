package movie;

public class MovieListing {

	protected enum screenType {
		TWO_D, THREE_D
	};

	protected enum dayOfWeek {
		MON, TUES, WED, THURS, FRI, SAT, SUN
	};

	private int movieListingID;
	private Movie movie;
	private screenType type;
	private dayOfWeek day;
	private int showtime;
	private int cinemaHall;
	private Review[] reviews;

	public MovieListing(int movieListingID, Movie movie, screenType type, dayOfWeek day, int showtime, int cinemaHall) {
		this.movieListingID = movieListingID;
		this.movie = movie;
		this.type = type;
		this.day = day;
		this.showtime = showtime;
		this.cinemaHall = cinemaHall;
		Review[] reviews = new Review[1];
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

	public Review[] getReviews() {
		return reviews;
	}

	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}

	public double getOverallRating() {
		double total = 0;
		for (int i = 0; i < reviews.length; i++) {
			total = total + reviews[i].getRating();
		}
		return Math.round(total / reviews.length * 10) / 10;
	}

	public void displayReviews() {
		for (int i = 0; i < reviews.length; i++) {
			System.out.println(reviews[i].getReview());
		}
	}

	public void addReview(Review review) {
		Review[] temp = new Review[reviews.length + 1];
		System.arraycopy(reviews, 0, temp, 0, reviews.length);
		temp[temp.length - 1] = review;
		reviews = temp;
	}

}
