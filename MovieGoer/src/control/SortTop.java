package control;

import java.util.ArrayList;
import java.util.List;

import entity.Movie;

public class SortTop {
	private List<Movie> movies = new ArrayList<Movie>();

	public SortTop(List<Movie> movies) {
		this.movies = movies;
	}

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
			System.out.printf("%d. %s", j + 1, curTop.getMovieTitle());
			movies.remove(curTop);
			curTop = movies.get(0);
		}
	}

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
			System.out.printf("%d. %s", j + 1, curTop.getMovieTitle());
			movies.remove(curTop);
			curTop = movies.get(0);
		}
	}
}
