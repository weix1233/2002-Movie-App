package movie;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Hall {
	@CsvBindByName
	protected int hallID;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> availableShowTimes;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> showTimes;
	private List<MovieListing> movieListing = new ArrayList<MovieListing>();

	public Hall(int hallID, List<String> availableShowTimes, List<String> showTimes) {
		this.hallID = hallID;
		this.availableShowTimes = availableShowTimes;
		this.showTimes = showTimes;
	}

	public Hall() {
		// TODO Auto-generated constructor stub
	}
	public int getHallID() {
		return this.hallID;
	}
	
	public List<String> getAvailableShowTimes(){
		return availableShowTimes;
	}
	
	public List<String> getShowTimes(){
		return showTimes;
	}
	
	public void setHallID(int split) {
		this.hallID = split;
	}
	
	public List<MovieListing> getMovieListing(){
		return movieListing;
	}
	
	public void setAvailableShowTimes(String ast) {
		List<String> s = new ArrayList<String>();
		String[] split = ast.split(",");
		for(int i = 0;i < split.length;i++)
			s.add(split[i]);
		this.availableShowTimes = s;
	}
	public void setShowTimes(String st) {
		List<String> s = new ArrayList<String>();
		String[] split = st.split(",");
		for(int i = 0;i < split.length;i++)
		s.add(split[i]);
		this.showTimes = s;
	}
	public void addShow(int timeSlot) {

	}

	public void delShow(int timeSlot) {

	}
}
