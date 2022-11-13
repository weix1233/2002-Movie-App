package boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.ReadCSVFiles;
import control.WriteCSVFiles;
import entity.Options;

/**
 * Allows the admin to change the configurations for the app
 * @author SS4 Group 4
 *
 */
public class AdminOptions {
	/**
	 * Scanner object
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * Displays the menu for choosing the options
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void displayOptions() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalStateException, IOException {
		System.out.println("Options Menu");
		System.out.println("1 - Change the sort option");
		System.out.println("2 - Change the base price");
		int choice = 0;
		do {
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				this.readAndSetSort();
				break;
			case 2:
				this.readAndSetBasePrice();
				break;
			}
		} while(choice  < 1 && choice > 2);
	}
	/**
	 * Prints and allows user to change the sort options
	 * @throws IllegalStateException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public void readAndSetSort() throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//read some value in the csv
		List<Options> o = ReadCSVFiles.getOptions();	
		System.out.printf("Sort by (1) Rating, (2) Sales, (3) Customer choice.\nCurrent option: (%d)\nChoose new option:\n",o.get(0).getOption());
		o.get(0).setOption(sc.nextInt());
		WriteCSVFiles.optionsToCSV(o);
	}
	/**
	 * Prints and allows the users to change the base price value
	 * @throws IllegalStateException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public void readAndSetBasePrice() throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<Options> o = ReadCSVFiles.getOptions();
		System.out.printf("Current base price: %.2f\nEnter new base price",o.get(0).getBasePrice());
		o.get(0).setBasePrice(sc.nextDouble());
		WriteCSVFiles.optionsToCSV(o);
	}
}	
