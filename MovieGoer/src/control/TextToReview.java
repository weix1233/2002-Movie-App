package control;

import com.opencsv.bean.AbstractCsvConverter;

import entity.Review;

/**
 * Utility class to convert text in CSV file to a Review object
 * 
 * @author SS4 Group 4
 *
 */
public class TextToReview extends AbstractCsvConverter {
	/**
	 * Converts the text information in CSV file to a Review object
	 */
	@Override
	public Object convertToRead(String value) {
		Review review = new Review();
		String[] split = value.split("\\|", 3);
		review.setReview(split[0]);
		review.setRating(Integer.parseInt(split[1]));
		review.setReviewer(split[2]);
		return review;
	}

}