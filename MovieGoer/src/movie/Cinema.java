package movie;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Cinema {
	@CsvBindByName
	private String name;
	@CsvBindAndSplitByName(elementType = Hall.class, collectionType = ArrayList.class, splitOn = "," , converter = TextToHall.class)
	private List<Hall> halls;
	
	public Cinema(List<Hall> halls, String name) {
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
	public List<Integer> getHallID() {
		List<Integer> hallID = new ArrayList<Integer>();
		for(int i = 0;i < halls.size();i++) {
			hallID.add(halls.get(i).getHallID());
		}
		return hallID;
	}
	
	public List<String> getsAST(int hallID){
		return halls.get(hallID).getAvailableShowTimes();
	}
	
	public List<String> getST(int hallID){
		return halls.get(hallID).getShowTimes();
	}
}
