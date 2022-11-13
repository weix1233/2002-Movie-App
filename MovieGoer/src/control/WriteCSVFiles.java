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
import entity.User;

public class WriteCSVFiles {

	private static String userFilePath = "C:\\Users\\user\\git\\2002-Movie-Appp\\MovieGoer\\database\\user\\user.csv";
	private static String movieFilePath = "C:\\Users\\user\\git\\2002-Movie-Appp\\MovieGoer\\database\\movie\\movie.csv";
	private static String MLDOFilePath = "C:\\Users\\user\\git\\2002-Movie-Appp\\MovieGoer\\database\\cinema\\";
	/**
	 * Updates the movie CSV
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
	public static void MLDOToCSV(List<MLDataObject> MLDOBeans,int locID) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		String ext = null;
		switch(locID) {
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
		Writer writer = new FileWriter(MLDOFilePath+ext);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(MLDOBeans);
		writer.close();
	}
}
