package control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import entity.Cinema;
import entity.MLDataObject;
import entity.Movie;
import entity.MovieListing;
import entity.Options;
import entity.User;

/**
 * Utility class containing all the functions to read and extract information
 * from CSV files into a List object. All the CSV files paths are declared here
 * for convenient editing and applies to the entire app.
 * 
 * @author SS4 Group 4
 *
 */
public class ReadCSVFiles {

	/**
	 * Location of the cinema CSV
	 */
	private static String cinemaFileName = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\cinema.csv";
	/**
	 * Location of the movie CSV
	 */
	private static String movieFileName = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\movie\\movie.csv";
	/**
	 * Location of the user CSV
	 */
	private static String userFilePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv";
	/**
	 * Location of the options CSV
	 */
	private static String optionsFilePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\options\\options.csv";

	/**
	 * Reads the user.csv file and compiles them into a List object
	 * 
	 * @return a list of User objects
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public static List<User> getLoginDetail() throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(userFilePath)).withType(User.class).build().parse();
	}

	/**
	 * Reads the cinema.csv file and compiles the information into a List of Cinema
	 * objects
	 * 
	 * @return a list of Cinema objects
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public static List<Cinema> getCinemaList() throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build().parse();
	}

	/**
	 * Reads the movie.csv file and compiles the information into a List of movie
	 * objects
	 * 
	 * @return a list of movie objects
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public static List<Movie> getMovieList() throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(movieFileName)).withType(Movie.class).build().parse();
	}

	/**
	 * Gets the options
	 * 
	 * @return options for the App
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public static List<Options> getOptions() throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder(new FileReader(optionsFilePath)).withType(Options.class).build().parse();
	}

	/**
	 * Reads the selected cineplex file which user has chosen and returns the list
	 * of MovieListing for that particular cineplex
	 * 
	 * @param choice user input from console interface prompt indicating location
	 * @return a list of MovieListing objects
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public static List<MovieListing> initialiseML(int choice) throws IllegalStateException, FileNotFoundException {
		String filePath = null;
		switch (choice) {
		case 1:
			filePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\jurong\\jurong.csv";
			break;
		case 2:
			filePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\orchard\\orchard.csv";
			break;
		case 3:
			filePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\yishun\\yishun.csv";
			break;
		default:
			filePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\jurong\\jurong.csv";
			break;
		}
		List<MLDataObject> mldoBeans = new CsvToBeanBuilder(new FileReader(filePath)).withType(MLDataObject.class)
				.build().parse();
		MLDOControl mlControl = new MLDOControl();
		List<MovieListing> mlBeans = mlControl.convertToML(mldoBeans);
		return mlBeans;
	}

	/**
	 * Because everytime we read a new userBeans, the memory address changes Hence,
	 * we need to refind the correct object in order to update the correct object
	 * 
	 * @param userBeans List of User objects
	 * @param user      the User object to find
	 * @return the found User object
	 */
	public static User findUser(List<User> userBeans, User user) {
		User newUser = null;
		for (int i = 0; i < userBeans.size(); i++) {
			if (userBeans.get(i).getUsername().equals(user.getUsername())) {
				if (userBeans.get(i).getPassword().equals(user.getPassword())) {
					newUser = userBeans.get(i);
				}
			}
		}
		if (user.getUsername() == null && user.getPassword() == null) {
			newUser = userBeans.get(userBeans.size() - 1);
		}
		return newUser;
	}
}
