package admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.Movie;

public class ControlPanel {

	private static void WelcomeBanner() {
		System.out.println("Welcome to the movie admin console. You can edit movie information here");
	}

	public static void main(String[] args)
			throws IllegalStateException, FileNotFoundException, NoSuchAlgorithmException {
		WelcomeBanner();
		StaffLogin sl = new StaffLogin();
		int userType = 0;
		// Guest = 1, Members = 2, Admin = 3, Login Error = -1 user is forced to login
		// again
		do {
			// userType = sl.validate();
			userType = 3;
		} while (userType == -1);

		switch (userType) {
		case 1:
			// guest
			break;
		case 2:
			// member
			break;
		case 3:
			// admin
			System.out.println("Find a movie!");
			String movieFilePath = "C:\\Users\\tanju\\Desktop\\movie\\movie.txt";
			List<Movie> beans = new CsvToBeanBuilder(new FileReader(movieFilePath)).withType(Movie.class).build()
					.parse();
			String watch = "Life Of Pi";
			Movie ref = null;
			for (int i = 0; i < beans.size(); i++) {
				if (watch.equals(beans.get(i).getMovieTitle())) {
					ref = beans.get(i);
				}
			}
			System.out.println(ref.getCast());
			break;
		}
	}

}
