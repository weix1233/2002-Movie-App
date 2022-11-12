package entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

public class MLDataObject {

	@CsvBindByName
	public String type;
	/**
	 * Day of the week or whether it's a public holiday
	 * @author SS4 Group 4
	 *
	 */
	@CsvBindByName
	public String day;
	/**	
	 * Title of the movie that is showing in the listing
	 */
	@CsvRecurse
	private Movie movie;
	/**
	 * Type of screen
	 */
	@CsvBindByName
	private String showTime;
	/**
	 * ID of the hall where the movie will be screened
	 */
	@CsvBindByName
	private int hallID;
	
	public MLDataObject(String type, String day, Movie movie,String showTime,int id) {
		this.type = type;
		this.day = day;
		this.movie = movie;
		this.showTime = showTime;
		this.hallID = id;
	}
	public MLDataObject() {}
	public String getScreenType() {
		return this.type;
	}
	public String getDayOfWeek() {
		return this.day;
	}
	public Movie getMovie() {
		return this.movie;
	}
	public String getShowTime() {
		return this.showTime;
	}
	public int getHallID() {
		return this.hallID;
	}
	public void setType(String st) {
		this.type = st;
	}
	public void setDayOfWeek(String day) {
		this.day = day;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public void setShowTime(String st) {
		this.showTime = st;
	}
	public void setHallID(int id) {
		this.hallID = id;
	}
}

