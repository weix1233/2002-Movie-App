package movie;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Hall {
	@CsvBindByName
	protected String cinemaID;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ",")
	private List<String> availableShowTimes;
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ",")
	private List<String> showTimes;

	public Hall(String cinemaID, List<String> availableShowTimes, List<String> showTimes) {
		this.cinemaID = cinemaID;
		this.availableShowTimes = availableShowTimes;
		this.showTimes = showTimes;
	}

	public Hall() {
		// TODO Auto-generated constructor stub
	}

	public void addShow(int timeSlot) {

	}

	public void delShow(int timeSlot) {

	}
}
