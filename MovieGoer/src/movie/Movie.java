package movie;

public class Movie {
	private String movieTitle;
	private String synopis;
	private String director;
	private String[] cast;

	public Movie(String movieTitle, String synopis, String director, String[] cast) {
		this.movieTitle = movieTitle;
		this.synopis = synopis;
		this.director = director;
		this.cast = cast;
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

	public void setCast(String[] cast) {
		this.cast = cast;
	}

}
