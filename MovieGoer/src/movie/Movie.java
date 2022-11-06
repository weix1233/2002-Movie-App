package movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

public class Movie {
	Scanner sc = new Scanner(System.in);

	@CsvBindByName
	private String movieTitle;
	@CsvBindByName
	private String synopis;
	@CsvBindByName
	private String director;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ",")
	private List<String> cast;
	@CsvBindByName(column = "showingStatus")
	private String showingStatus;
	@CsvBindByName(column = "ageRating")
	private String ageRating;
	// @CsvBindAndSplitByName(elementType = String.class, splitOn = ",")
	// private String[] cast;
	// private int castPointer;
	private List<Review> reviews = new ArrayList<Review>();
	private int saleCounter;

	public Movie() {
	};

	public Movie(String movieTitle, String synopis, String director, List<String> cast, String showingStatus,
			String ageRating) {
		this.movieTitle = movieTitle;
		this.synopis = synopis;
		this.director = director;
		this.cast = cast;
		this.showingStatus = showingStatus;
		this.ageRating = ageRating;
		this.saleCounter = 0;
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

	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}

	public List<String> getCast() {
		return cast;
	}

	public void addCastMember(String castName) {
		cast.add(castName);
	}

	public void removeCastMember(int castPosition) {
		cast.remove(castPosition);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Movie> getMovieList(String path) throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(path)).withType(Movie.class).build().parse();
	}

	public void printMovies(List<Movie> beans) {
		for (int i = 0; i < beans.size(); i++) {
			System.out.printf(
					"%s. Title: %s | Synopis: %s | Director: %s | Cast: %s | ShowingStatus: %s | Age Rating: %s \n",
					Integer.toString(i), beans.get(i).getMovieTitle(), beans.get(i).getSynopis(),
					beans.get(i).getDirector(), beans.get(i).getCast(), beans.get(i).getShowingStatus(),
					beans.get(i).getAgeRating());
		}
	}

	public void printCurrentMovieList(List<Movie> beans) {
		for (int i = 0; i < beans.size(); i++) {
			if (beans.get(i).getShowingStatus().equals("NOW_SHOWING")) {
				System.out.println(
						i + ". Title: " + beans.get(i).getMovieTitle() + " | Age Rating: " + beans.get(i).ageRating);
			}
		}
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

	public int getSales() {
		return this.saleCounter;
	}

	public void addSales() {
		this.saleCounter++;
	}

	public String getShowingStatus() {
		return showingStatus;
	}

	public void setShowingStatus(String status) {
		this.showingStatus = status;
	}
	/*
	 * public String[] getCast() { return cast; }
	 * 
	 * public void addCast(String cast) { if (castPointer > this.cast.length - 1) {
	 * System.out.println("Already maximum number of cast"); } else {
	 * this.cast[castPointer] = cast; castPointer++; } }
	 */

	public String getAgeRating() {
		return ageRating;
	}

}
