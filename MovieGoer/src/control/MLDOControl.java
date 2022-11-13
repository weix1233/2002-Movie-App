package control;

import java.util.ArrayList;
import java.util.List;

import entity.MLDataObject;
import entity.MovieListing;
import entity.MovieListing.dayOfWeek;
import entity.MovieListing.screenType;
/**
 * Utility functions for the MovieListingDataObject (MLDO)
 * @author SS4 Group 4
 *
 */
public class MLDOControl {
	/**
	 * Empty constructor to create the object
	 */
	public MLDOControl() {}
	/**
	 * Converts the MLDO into a list of movie listings
	 * @param data The list of MLDO
	 * @return converted MovieListings
	 */
	public List<MovieListing> convertToML(List<MLDataObject> data){
		List<MovieListing> ml = new ArrayList<MovieListing>();
		for(int i = 0;i < data.size();i++) {
			MovieListing temp = new MovieListing();
			ml.add(temp);
			ml.get(i).setShowtime(data.get(i).getShowTime());
			if(data.get(i).getScreenType().equals("THREE_D")) {
				ml.get(i).setType(screenType.THREE_D);
			}
			else ml.get(i).setType(screenType.TWO_D);
			switch(data.get(i).getDayOfWeek()){
			case "MON":
				ml.get(i).setDay(dayOfWeek.MON);
				break;
			case "TUES":
				ml.get(i).setDay(dayOfWeek.TUES);
				break;
			case "WED":
				ml.get(i).setDay(dayOfWeek.WED);
				break;
			case "THURS":
				ml.get(i).setDay(dayOfWeek.THURS);
				break;
			case "FRI":
				ml.get(i).setDay(dayOfWeek.FRI);
				break;
			case "SAT":
				ml.get(i).setDay(dayOfWeek.SAT);
				break;
			case "SUN":
				ml.get(i).setDay(dayOfWeek.SUN);
				break;
			case "PH":
				ml.get(i).setDay(dayOfWeek.PH);
				break;
			default:
				ml.get(i).setDay(dayOfWeek.MON);
				break;
			}
			ml.get(i).setCinemaHall(data.get(i).getHallID());
			ml.get(i).setMovie(data.get(i).getMovie());
			ml.get(i).setSeats(data.get(i).getSeats());
		}
		return ml;
	}
}
