package control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import boundary.Login;
import entity.Movie;

public class WriteCSVFiles {
	private static String loginMemberFilePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\login\\member.csv";
	private static String movieFilePath = "C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\movie\\movie.csv";
	
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

	public static void loginWriter(List<Login> loginBeans)
			throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		Writer writer = new FileWriter(loginMemberFilePath);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		beanToCsv.write(loginBeans);
		writer.close();
	}
}
