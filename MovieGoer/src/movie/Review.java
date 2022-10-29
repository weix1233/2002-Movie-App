package movie

public class Review {
	private String review;
	private int rating;
	private String reviewerName;
	
	public String getReview() {
		return this.review;
	}
	public int getRating() {
		return this.rating;
	}
	public String getReviewer() {
		return this.reviewerName;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setReviewer(String reviewer) {
		this.reviewerName = reviewer;
	}
}
