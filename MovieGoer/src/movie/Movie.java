package movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

public class Movie {
	Scanner sc = new Scanner(System.in);
	String movieFileName = "C:\\Users\\hue\\Desktop\\database\\movie\\movie.csv";

	public enum showingStatus {
		COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
	};

	public enum ageRating {
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
	@CsvBindByName(column = "showingStatus")
	private String showingStatus;
	@CsvBindByName(column = "ageRating")
	private String ageRating;

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

	public String getShowingStatus() {
		return showingStatus;
	}

	public String getAgeRating() {
		return ageRating;
	}
	
	public List<Movie> getMovieList(String path) throws IllegalStateException, FileNotFoundException{
		return new CsvToBeanBuilder(new FileReader(path)).withType(Movie.class).build().parse();
	}
	
	public void printCurrentMovieList(List<Movie> beans) {
		for(int i = 0; i < beans.size(); i++) {
			if(beans.get(i).getShowingStatus().equals("NOW_SHOWING")) {
				System.out.println(i + ". Title: " + beans.get(i).getMovieTitle() + " | Age Rating: " + beans.get(i).ageRating);
			}
		}
	}
/*
	public showingStatus setShowingStatus() {
		System.out.print("Choose showing status\n(1) COMING_SOON (2) PREVIEW (3) NOW_SHOWING (4) END_OF_SHOWING: ");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			return showingStatus.COMING_SOON;
		case 2:
			return showingStatus.PREVIEW;
		case 3:
			return showingStatus.NOW_SHOWING;
		case 4:
			return showingStatus.END_OF_SHOWING;
		default:
			System.out.println("Error in choice, defaulting to COMING_SOON");
			return showingStatus.COMING_SOON;
		}
	}
	*/
	/*
	public ageRating setAgeRating() {
		System.out.print("Choose age rating\n(1) PG (2) PG13 (3) NC16 (4) M18 (5) R21: ");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			return ageRating.PG;
		case 2:
			return ageRating.PG13;
		case 3:
			return ageRating.NC16;
		case 4:
			return ageRating.M18;
		case 5:
			return ageRating.R21;
		default:
			System.out.println("Error input, temporarily assigned PG");
			return ageRating.PG;
		}
	}
*/
	

	/*
	 * public String[] getCast() { return cast; }
	 * 
	 * public void addCast(String cast) { if (castPointer > this.cast.length - 1) {
	 * System.out.println("Already maximum number of cast"); } else {
	 * this.cast[castPointer] = cast; castPointer++; } }
	 */

}

