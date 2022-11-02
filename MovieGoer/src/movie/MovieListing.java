package movie;

public class MovieListing {

	private enum screenType {
		TWO_D, THREE_D
	};

	private enum showingStatus {
		COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
	};

	private enum ageRating {
		PG, PG13, NC16, M18, R21
	};

	private Movie movie;
	protected screenType type;
	protected showingStatus status;
	protected ageRating ageRate;
	private Review[] reviews;

	public MovieListing(Movie movie, screenType type, showingStatus status, ageRating ageRate) {
		this.movie = movie;
		this.type = type;
		this.status = status;
		this.ageRate = ageRate;
		Review[] reviews = new Review[1];
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

	public showingStatus getStatus() {
		return status;
	}

	public void setStatus(showingStatus status) {
		this.status = status;
	}

	public ageRating getAgeRate() {
		return ageRate;
	}

	public void setAgeRate(ageRating ageRate) {
		this.ageRate = ageRate;
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
