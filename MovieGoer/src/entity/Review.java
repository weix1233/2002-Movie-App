package entity;

import com.opencsv.bean.CsvBindByName;

/**
 * Represents a single review by a customer
 * @author SS4 Group 4
 *
 */
public class Review {
	/**
	 * Qualitative review of the movie
	 */
	@CsvBindByName
	private String review;
	/**
	 * Rating of the movie as an integer from 1 to 10
	 */
	@CsvBindByName
	private int rating;
	/** 
	 * First and last name of the reviewer
	 */
	@CsvBindByName
	private String reviewerName;
	/**
	 * Creates a new empty Review
	 */
	public Review() {
	}
	/**
	 * Creates a new Review with the following details
	 * @param review Qualitative review of the movie
	 * @param rating Rating of the movie out of 10
	 * @param reviewerName Name of the reviewer
	 */
	public Review(String review, int rating, String reviewerName) {
		this.review = review;
		this.rating = rating;
		this.reviewerName = reviewerName;
	}
	/**
	 * Changed the toString function in order for writer to write properly
	 */
	@Override
	public String toString() {
		String combi = null;
		combi = this.review + "|" + Integer.toString(rating) + "|" + this.reviewerName;
		return combi;
	}
	/**
	 * Gets the qualitative review of the movie as a String
	 * @return Qualitative review of the movie
	 */
	public String getReview() {
		return this.review;
	}
	/**
	 * Gets the rating of the movie
	 * @return Rating of the movie
	 */
	public int getRating() {
		return this.rating;
	}
	/**
	 * Gets the first and last name of the reviewer
	 * @return Name of the reviewer
	 */
	public String getReviewer() {
		return this.reviewerName;
	}
	/**
	 * Changes the review of this Review
	 * @param review New qualitative review of the movie
	 */
	public void setReview(String review) {
		this.review = review;
	}
	/**
	 * Changes the rating of this Review
	 * @param rating New rating of the movie as an integer from 1 to 10
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * Changes the name of the reviewer of this Review
	 * @param reviewer New name of the reviewer, should include both first and last name
	 */
	public void setReviewer(String reviewer) {
		this.reviewerName = reviewer;
	}
}
