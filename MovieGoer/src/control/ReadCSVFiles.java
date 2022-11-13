package control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import entity.Cinema;
import entity.MLDataObject;
import entity.Movie;
import entity.MovieListing;
import entity.User;

public class ReadCSVFiles {

	private static String cinemaFileName = "C:\\Users\\tanju\\git\\2002-Movie-Apppppp\\MovieGoer\\database\\cinema\\cinema.csv";
	private static String movieFileName = "C:\\Users\\tanju\\git\\2002-Movie-Apppppp\\MovieGoer\\database\\movie\\movie.csv";
	private static String userFilePath = "C:\\Users\\tanju\\git\\2002-Movie-Apppppp\\MovieGoer\\database\\user\\user.csv";

	public static List<User> getLoginDetail() throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(userFilePath)).withType(User.class).build().parse();
	}

	public static List<Cinema> getCinemaList() throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build().parse();
	}

	public static List<Movie> getMovieList() throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(movieFileName)).withType(Movie.class).build().parse();
	}

	public static List<MovieListing> initialiseML(int choice) throws IllegalStateException, FileNotFoundException {
		String filePath = null;
		switch (choice) {
		case 1:
			filePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\jurong\\jurong.csv";
			break;
		case 2:
			filePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\orchard\\orchard.csv";
			break;
		case 3:
			filePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\yishun\\yishun.csv";
			break;
		default:
			filePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\jurong\\jurong.csv";
			break;
		}
		List<MLDataObject> mldoBeans = new CsvToBeanBuilder(new FileReader(filePath)).withType(MLDataObject.class)
				.build().parse();
		MLDOControl mlControl = new MLDOControl();
		List<MovieListing> mlBeans = mlControl.convertToML(mldoBeans);
		return mlBeans;
	}
}
