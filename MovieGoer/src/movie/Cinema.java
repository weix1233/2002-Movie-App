package movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

public class Cinema {
	@CsvBindByName
	private String name;
	@CsvBindAndSplitByName(elementType = Hall.class, collectionType = ArrayList.class, splitOn = ",", converter = TextToHall.class)
	private List<Hall> halls;
	@CsvBindByName
	private String cinemaID;
	@CsvBindByName
	private boolean isPlatinum;

	public Cinema(List<Hall> halls, String name, String cid, boolean plat) {
		this.name = name;
		this.halls = halls;
		this.cinemaID = cid;
		this.isPlatinum = plat;
	}

	public Cinema() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Cinema> getCinemaList(String path) throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(path)).withType(Cinema.class).build().parse();
	}

	public List<Integer> getHallID() {
		List<Integer> hallID = new ArrayList<Integer>();
		for (int i = 0; i < halls.size(); i++) {
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

	public List<String> getAST(int hallID) {
		String part1 = halls.get(hallID).getAvailableShowTimes().get(0);
		List<String> items = Arrays.asList(part1.split(";"));

		return items;
	}

	public List<String> getST(int hallID) {
		String part1 = halls.get(hallID).getShowTimes().get(0);
		List<String> items = Arrays.asList(part1.split(";"));
		return halls.get(hallID).getShowTimes();
	}

	public List<MovieListing> getMovieList(int hallID) {
		return halls.get(hallID).getMovieListing();
	}

	public String getCinemaID() {
		return this.cinemaID;
	}

	public boolean getIP() {
		return this.isPlatinum;
	}
	public Hall getHall(int hallID) {
		return this.halls.get(hallID);
	}
}
