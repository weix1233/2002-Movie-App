package movie;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Cinema {
	@CsvBindByName
	protected String name;
	@CsvBindAndSplitByName(elementType = Hall.class, collectionType = ArrayList.class, splitOn = "," , converter = TextToHall.class)
	private List<Hall> halls;

	public Cinema(String name, List<Hall> halls) {
		this.name = name;
		this.halls = halls;
	}
	public Cinema() {}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCinemaID() {
		return halls.get(0).cinemaID;
	}
	public String getAST() {
		return halls.get(0).getAvailableShowTimes();
	}
	public String getST() {
		return halls.get(0).getShowTimes();
	}
}
