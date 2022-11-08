package movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class CinemaControl {
	public List<Cinema> getCinemaList(String path) throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(path)).withType(Cinema.class).build().parse();
	}

	public void getCinemaInfo(List<Cinema> beans, int c) {
		System.out.printf("Cinema ID: %s\n", beans.get(c).getCinemaID());
		System.out.printf("Name: %s\n", beans.get(c).getName());
		System.out.println("isPlatinum: " + beans.get(c).getIP());
		for (int j = 1; j < beans.get(c).getHallID().size(); j++) {
			System.out.println("Hall " + beans.get(c).getHallID().get(j));
			// System.out.println("Available Show Times: " + beans.get(c).getAST(j));
			// System.out.println("Show Times: " + beans.get(c).getST(j));
		}

	}
}
