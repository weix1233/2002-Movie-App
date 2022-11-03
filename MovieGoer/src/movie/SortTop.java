package movie;

import java.util.ArrayList;
import java.util.List;

public class SortTop {
	private List<Movie> movies = new ArrayList<Movie>();
	
	public SortTop(List<Movie> movies) {
		this.movies = movies;
	}
	
	public void sortByRating() {
		int curMax = 0;
		Movie curTop;
		int cur;
		for(int j=0; j<5; j++) {
			for(int i=0;i<movies.size();i++) {
				cur = movies.get(i).getOverallRating();
				if(cur>curMax) {
					curMax = cur;
					curTop = movies.get(i);
				}
			}
			System.out.printf("%d. %s", j+1, curTop.getMovieTitle());
			movies.remove(curTop);
		}
	}
	
	public void sortBySales() {
		int curMax = 0;
		Movie curTop;
		int cur;
		for(int j=0; j<5; j++) {
			for(int i=0;i<movies.size();i++) {
				cur = movies.get(i).getSales();
				if(cur>curMax) {
					curMax = cur;
					curTop = movies.get(i);
				}
			}
			System.out.printf("%d. %s", j+1, curTop.getMovieTitle());
			movies.remove(curTop);
		}
	}
}
