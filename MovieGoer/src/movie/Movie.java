package movie;

public class Movie {
	private String movieTitle;
	private String synopis;
	private String director;
	private String[] cast;
	private int castPointer;

	public Movie(String movieTitle, String synopis, String director, int numCast) {
		this.movieTitle = movieTitle;
		this.synopis = synopis;
		this.director = director;
		String[] cast = new String[numCast];
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

	public String[] getCast() {
		return cast;
	}
	public void addCast(String cast) {
		if(castPointer>this.cast.length-1) {
			System.out.println("Already maximum number of cast");
		}
		else {
			this.cast[castPointer] = cast;
			castPointer++;
		}
	}

}
