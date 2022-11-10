package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
/**
 * Represents a movie with all the relevant information
 * @author SS4 Group 4
 *
 */
public class Movie {
	/**
	 * Title of the movie
	 */
	@CsvBindByName
	private String movieTitle;
	/**
	 * Short synopsis describing the movie
	 */
	@CsvBindByName
	private String synopis;
	/**
	 * First and last name of the director
	 */
	@CsvBindByName
	private String director;
	/**
	 * List of cast members, with each member stored as a String of first and last name
	 */
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ",")
	private List<String> cast;
	/**
	 * Showing status of the movie
	 */
	@CsvBindByName(column = "showingStatus")
	private String showingStatus;
	/**
	 * Age rating of the movie
	 */
	@CsvBindByName(column = "ageRating")
	private String ageRating;
	/**
	 * List of all the reviews for the movie
	 */
	private List<Review> reviews;
	/**
	 * A counter of how many tickets have been sold for the movie
	 */
	private int saleCounter;
	/**
	 * Creates a new empty Movie
	 */
	public Movie() {
	};
	/**
	 * Creates a new Movie with the following parameters and initialising sales to 0
	 * @param movieTitle Title of the movie
	 * @param synopis Short synopsis describing the movie
	 * @param director Name of the director
	 * @param cast List of cast members
	 * @param showingStatus Showing status of the movie
	 * @param ageRating Age rating of the movie
	 */
	public Movie(String movieTitle, String synopis, String director, List<String> cast, String showingStatus,
			String ageRating) {
		this.movieTitle = movieTitle;
		this.synopis = synopis;
		this.director = director;
		this.cast = cast;
		this.showingStatus = showingStatus;
		this.ageRating = ageRating;
		this.saleCounter = 0;
		reviews = new ArrayList<Review>();
	}
	/**
	 * Gets the title of the movie
	 * @return The movie title
	 */
	public String getMovieTitle() {
		return movieTitle;
	}
	/**
	 * Changes the title of the movie
	 * @param movieTitle The new movie title
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	/**
	 * Gets the synopsis of the movie
	 * @return The movie synopsis
	 */
	public String getSynopis() {
		return synopis;
	}
	/**
	 * Changes the synopsis of the movie
	 * @param synopis The new movie synopsis
	 */
	public void setSynopis(String synopis) {
		this.synopis = synopis;
	}
	/**
	 * Gets the director of the movie
	 * @return The movie's director
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * Changes the director of the movie
	 * @param director The new movie director
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * Gets the age rating of the movie
	 * @return The movie's age rating
	 */
	public String getAgeRating() {
		return ageRating;
	}
	/**
	 * Changes the age rating of the movie
	 * @param ageRating The new age rating
	 */
	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}
	/**
	 * Gets a list of the movie's cast
	 * @return List of movie's cast
	 */
	public List<String> getCast() {
		return cast;
	}
	/**
	 * Adds a cast member to the list of cast members
	 * @param castName First and last name of new cast member
	 */
	public void addCastMember(String castName) {
		cast.add(castName);
	}
	/**
	 * Removes a cast member from the list of cast members
	 * @param castIndex Index of cast member to be removed
	 */
	public void removeCastMember(int castPosition) {
		cast.remove(castPosition);
	}
	/**
	 * Gets the list of reviews for the movie
	 * @return List of Review
	 */
	public List<Review> getReviews() {
		return reviews;
	}
	/**
	 * Changes the list of reviews for the movie
	 * @param reviews A list of Review
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	/**
	 * Calculates and returns the average rating for the movie
	 * @return Average rating of the movie
	 */
	public double getOverallRating() {
		double total = 0;
		for (int i = 0; i < reviews.size(); i++) {
			total = total + reviews.get(i).getRating();
		}
		return Math.round(total / reviews.size() * 10) / 10;
	}
	/**
	 * Prints each qualitative review in the list of reviews of the movie
	 */
	public void displayReviews() {
		for (int i = 0; i < reviews.size(); i++) {
			System.out.println(reviews.get(i).getReview());
		}
	}
	/**
	 * Adds a new Review to the list of Review
	 * @param review The new Review object
	 */
	public void addReview(Review review) {
		reviews.add(review);
	}
	/**
	 * Gets the total number of tickets sold for the movie
	 * @return Total ticket sales
	 */
	public int getSales() {
		return this.saleCounter;
	}
	/**
	 * Increments the total number of tickets sold by 1
	 */
	public void addSales() {
		this.saleCounter++;
	}
	/**
	 * Changes the total number of tickets sold to a new value
	 * @param sales New total ticket sales
	 */
	public void setSales(int sales) {
		this.saleCounter = sales;
	}
	/**
	 * Gets showing status of the movie
	 * @return The movie's current showing status
	 */
	public String getShowingStatus() {
		return showingStatus;
	}
	/**
	 * Changes the showing status of the movie
	 * @param status The new showing status
	 */
	public void setShowingStatus(String status) {
		this.showingStatus = status;
	}
	
	

}
