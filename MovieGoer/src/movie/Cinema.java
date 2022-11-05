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
	@CsvBindByName
	private String cinemaID;
	@CsvBindByName
	private boolean isPlatinum;
	
	public Cinema(List<Hall> halls, String name,String cid,boolean plat) {
		this.name = name;
		this.halls = halls;
		this.cinemaID = cid;
		this.isPlatinum = plat;
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
	
	public void setIP(boolean plat) {
		this.isPlatinum = plat;
	}
	public void setCinemaID(String cid) {
		this.cinemaID = cid;
	}
	public List<String> getsAST(int hallID){
		return halls.get(hallID).getAvailableShowTimes();
	}
	
	public List<String> getST(int hallID){
		return halls.get(hallID).getShowTimes();
	}
	public String getCinemaID() {
		return this.cinemaID;
	}
	public boolean getIP() {
		return this.isPlatinum;
	}
}
