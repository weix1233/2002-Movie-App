package movie;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Movie {
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
	private Review[] reviews;

	public Movie() {
	};

	public Movie(String movieTitle, String synopis, String director, List<String> cast) {
		this.movieTitle = movieTitle;
		this.synopis = synopis;
		this.director = director;
		this.cast = cast;
		Review[] reviews = new Review[1];
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getSynopis() {
		return synopis;
	}

	public void setSynopis(String synopis) {
		this.synopis = synopis;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<String> getCast() {
		return cast;
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
	/*
	 * public String[] getCast() { return cast; }
	 * 
	 * public void addCast(String cast) { if (castPointer > this.cast.length - 1) {
	 * System.out.println("Already maximum number of cast"); } else {
	 * this.cast[castPointer] = cast; castPointer++; } }
	 */

}
