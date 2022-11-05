package movie;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Hall {
	@CsvBindByName
	protected String cinemaID;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> availableShowTimes;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private List<String> showTimes;

	public Hall(String cinemaID, List<String> availableShowTimes, List<String> showTimes) {
		this.cinemaID = cinemaID;
		this.availableShowTimes = availableShowTimes;
		this.showTimes = showTimes;
	}

	public Hall() {
		// TODO Auto-generated constructor stub
	}
	public String getCinemaID() {
		return this.cinemaID;
	}
	public String getAvailableShowTimes(){
		String print = new String();
		for(int i = 0;i < this.showTimes.size(); i++)
			print = print + this.showTimes.get(i);
		return print;
	}
	public String getShowTimes(){
		String print = new String();
		for(int i = 0;i < this.showTimes.size(); i++)
			print = print + this.showTimes.get(i);
		return print;
	}
	public void setCinemaID(String id) {
		this.cinemaID = id;
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
