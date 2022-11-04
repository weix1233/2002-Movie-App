package movie;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;

public class Cinema {
	protected String name;
	@CsvBindAndSplitByName(elementType = Hall.class, collectionType = ArrayList.class, splitOn = ",")
	private List<Hall> halls;

	public Cinema(String name, List<Hall> halls) {
		this.name = name;
		this.halls = halls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
