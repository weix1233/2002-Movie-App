package movie;

public class MovieListing {
	public enum screenType {
		TWO_D, THREE_D
	};

	public enum showingStatus {
		COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
	};

	private enum ageRating {
		PG, PG13, NC16, M18, R21
	};

	private Movie movie;
	protected screenType type;
	protected showingStatus status;
	protected ageRating ageRate;

	public MovieListing(Movie movie, screenType type, showingStatus status, ageRating ageRate) {
		this.movie = movie;
		this.type = type;
		this.status = status;
		this.ageRate = ageRate;
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

}
