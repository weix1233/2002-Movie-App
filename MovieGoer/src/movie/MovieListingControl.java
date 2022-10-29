package movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MovieListingControl implements MovieListingInterface {
	Scanner sc;

	@Override
	public MovieListing addMovieListing(MovieListing listing) {
		// TODO Auto-generated method stub

		try {
			sc = new Scanner(new File("C:\\Users\\tanju\\Desktop\\movieListing.csv"));
			sc.useDelimiter(",");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeMovieListing(MovieListing listing) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMovieListing(MovieListing listing) {
		// TODO Auto-generated method stub

	}

}
