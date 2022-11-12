package control;

import java.util.ArrayList;
import java.util.List;

import entity.Movie;
/**
 * Utility class to display a list of the top 5 movies.
 * List can be sorted by either rating or by sales.
 * @author SS4 Group 4
 *
 */
public class SortTop {
	/**
	 * A list of movies being shown at the moment.
	 */
	private List<Movie> movies = new ArrayList<Movie>();
	/**
	 * Creates a new SortTop with a list of movies
	 * @param movies A list of the movies currently being shown
	 */
	public SortTop(List<Movie> movies) {
		this.movies = movies;
	}
	/**
	 * Sorts the movies by the overall rating and prints the top 5
	 */
	public void sortByRating() {
		double curMax = 0;
		Movie curTop = movies.get(0);
		double cur;
		for (int j = 0; j < 5; j++) {
			for (int i = 1; i < movies.size(); i++) {
				cur = movies.get(i).getOverallRating();
				if (cur > curMax) {
					curMax = cur;
					curTop = movies.get(i);
				}
			}
			System.out.printf("%d. %s\n", j + 1, curTop.getMovieTitle());
			movies.remove(curTop);
			curTop = movies.get(0);
		}
	}
	/**
	 * Sorts the movies by the total sales and prints the top 5
	 */
	public void sortBySales() {
		int curMax = 0;
		Movie curTop = movies.get(0);
		int cur;
		for (int j = 0; j < 5; j++) {
			for (int i = 1; i < movies.size(); i++) {
				cur = movies.get(i).getSales();
				if (cur > curMax) {
					curMax = cur;
					curTop = movies.get(i);
				}
			}
			System.out.printf("%d. %s\n", j + 1, curTop.getMovieTitle());
			movies.remove(curTop);
			curTop = movies.get(0);
		}
	}
}
