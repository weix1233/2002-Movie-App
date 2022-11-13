package control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import entity.MLDataObject;
import entity.Movie;
import entity.Options;
import entity.User;

/**
 * Utility class which contains all the functions to write a List of objects
 * back into the csv file. All file paths are declared here for editing
 * convenience and must be modified to fit your own file structure.
 * 
 * @author SS4 Group 4
 *
 */
public class WriteCSVFiles {
	/**
	 * Location of the user CSV
	 */
	private static String userFilePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv";
	/**
	 * Location of the movie CSV
	 */
	private static String movieFilePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\movie\\movie.csv";
	/**
	 * Location of the cinema CSV
	 */
	private static String MLDOFilePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\";
	/**
	 * Location of the options CSV
	 */
	private static String optionsFilePath = "C:\\Users\\tanju\\git\\2002-Movie-App\\MovieGoer\\database\\options\\options.csv";

	/**
	 * Updates the movie CSV
	 * 
	 * @param movieBeans the movie list
	 * @throws IllegalStateException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */

	public static void movieToCSV(List<Movie> movieBeans)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		Writer writer = new FileWriter(movieFilePath);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(movieBeans);
		writer.close();
	}

	/**
	 * Updates the user CSV
	 * 
	 * @param userBeans the user list
	 * @throws IllegalStateException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public static void userToCSV(List<User> userBeans)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		Writer writer = new FileWriter(userFilePath);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(userBeans);
		writer.close();
	}

	/**
	 * Updates the Movie Listing Data Object
	 * 
	 * @param MLDOBeans List of MLDataObjects
	 * @param locID     Location ID of the cinema
	 * @throws IOException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 */
	public static void MLDOToCSV(List<MLDataObject> MLDOBeans, int locID)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		String ext = null;
		switch (locID) {
		case 1:
			ext = "jurong\\jurong.csv";
			break;
		case 2:
			ext = "orchard\\orchard.csv";
			break;
		case 3:
			ext = "yishun\\yishun.csv";
			break;
		default:
			ext = "jurong\\jurong.csv";
			break;
		}
		Writer writer = new FileWriter(MLDOFilePath + ext);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(MLDOBeans);
		writer.close();
	}

	/**
	 * Changes the stored memory for options
	 * 
	 * @param optionsBeans the beans read by the admin
	 * @throws IllegalStateException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public static void optionsToCSV(List<Options> optionsBeans)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		Writer writer = new FileWriter(optionsFilePath);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(optionsBeans);
		writer.close();
	}
}
