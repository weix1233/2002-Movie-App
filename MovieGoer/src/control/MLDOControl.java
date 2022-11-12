package control;

import java.util.ArrayList;
import java.util.List;

import entity.MLDataObject;
import entity.MovieListing;
import entity.MovieListing.dayOfWeek;
import entity.MovieListing.screenType;

public class MLDOControl {
	public MLDOControl() {}
	public List<MovieListing> convertToML(List<MLDataObject> data){
		List<MovieListing> ml = new ArrayList<MovieListing>();
		MovieListing temp = new MovieListing();
		for(int i = 0;i < data.size();i++) {
			temp.setShowtime(data.get(i).getShowTime());
			if(data.get(i).getScreenType().equals("THREE_D")) {
				temp.setType(screenType.THREE_D);
			}
			else temp.setType(screenType.TWO_D);
			switch(data.get(i).getDayOfWeek()){
			case "MON":
				temp.setDay(dayOfWeek.MON);
			case "TUES":
				temp.setDay(dayOfWeek.TUES);
			case "WED":
				temp.setDay(dayOfWeek.WED);
			case "THURS":
				temp.setDay(dayOfWeek.THURS);
			case "FRI":
				temp.setDay(dayOfWeek.FRI);
			case "SAT":
				temp.setDay(dayOfWeek.SAT);
			case "SUN":
				temp.setDay(dayOfWeek.SUN);
			default:
				temp.setDay(dayOfWeek.MON);
			}
			temp.setCinemaHall(data.get(i).getHallID());
			temp.setMovie(data.get(i).getMovie());
			ml.add(temp);
		}
		return ml;
	}
}
