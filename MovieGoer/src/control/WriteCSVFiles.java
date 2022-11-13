package control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import entity.Movie;
import entity.User;

public class WriteCSVFiles {
	private static String userFilePath = "C:\\Users\\tanju\\git\\2002-Movie-Apppppp\\MovieGoer\\database\\user\\user.csv";
	private static String movieFilePath = "C:\\Users\\tanju\\git\\2002-Movie-Apppppp\\MovieGoer\\database\\movie\\movie.csv";

	/**
	 * Updates the review in the csv
	 * 
	 * @param path       file path
	 * @param movieBeans the movie list
	 * @throws IllegalStateException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public static void reviewWriter(String path, List<Movie> movieBeans)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		Writer writer = new FileWriter(movieFilePath);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(movieBeans);
		writer.close();
	}

	public static void loginWriter(List<User> userBeans)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		Writer writer = new FileWriter(userFilePath);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(userBeans);
		writer.close();
	}
}
