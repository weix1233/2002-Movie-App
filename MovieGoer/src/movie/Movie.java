package movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Movie {
	Scanner sc = new Scanner(System.in);

	protected enum showingStatus {
		COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
	};

	protected enum ageRating {
		PG, PG13, NC16, M18, R21
	};

	@CsvBindByName
	private String movieTitle;
	@CsvBindByName
	private String synopis;
	@CsvBindByName
	private String director;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ",")
	private List<String> cast;

	// @CsvBindAndSplitByName(elementType = String.class, splitOn = ",")
	// private String[] cast;
	// private int castPointer;
	private List<Review> reviews = new ArrayList<Review>();


	public Movie() {
	};

	public Movie(String movieTitle, String synopis, String director, List<String> cast, showingStatus showingStatus,
			ageRating ageRating) {
		this.movieTitle = movieTitle;
		this.synopis = synopis;
		this.director = director;
		this.cast = cast;
		this.showingStatus = showingStatus;
		this.ageRating = ageRating;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getSynopis() {
		return synopis;
	}

	public String getDirector() {
		return director;
	}

	public List<String> getCast() {
		return cast;
	}
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public double getOverallRating() {
		double total = 0;
		for (int i = 0; i < reviews.size(); i++) {
			total = total + reviews.get(i).getRating();
		}
		return Math.round(total / reviews.size() * 10) / 10;
	}

	public void displayReviews() {
		for (int i = 0; i < reviews.size(); i++) {
			System.out.println(reviews.get(i).getReview());
		}
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	/*
	 * public String[] getCast() { return cast; }
	 * 
	 * public void addCast(String cast) { if (castPointer > this.cast.length - 1) {
	 * System.out.println("Already maximum number of cast"); } else {
	 * this.cast[castPointer] = cast; castPointer++; } }
	 */

}
