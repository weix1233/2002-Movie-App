package admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.Cinema;

public class AdminControl {
	Scanner sc = new Scanner(System.in);

	public void MainMenu() throws IllegalStateException, FileNotFoundException {
		System.out.print("\n(1) Access Cineplex database (2) Edit movie database: ");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			MovieMenu();
			break;
		case 2:
			break;
		default:
			break;
		}

	}

	public void MovieMenu() throws IllegalStateException, FileNotFoundException {
		String cinemaFileName = "C:\\Users\\tanju\\Desktop\\database\\cinema\\cinema.csv";
		String hallFileName = "C:\\User\\tanju\\Desktop\\database\\cinema\\";
		List<Cinema> cinemaBeans = new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build()
				.parse();
		/*
		 * System.out.
		 * print("Choose Location\n(1) Jurong CV (2) Punggol CV (3) Sengkang CV: "); int
		 * location = sc.nextInt(); String locationFolder; switch (location) { case 1:
		 * locationFolder = "jurong"; break; default: locationFolder = "jurong"; }
		 * 
		 * System.out.println("Choose cinema hall 1 ~ 3"); int hall = sc.nextInt();
		 * String hallFile; switch (hall) { case 1: hallFile = "\\hall1.csv"; break;
		 * default: hallFile = "\\hall1.csv"; }
		 * 
		 * hallFileName += locationFolder + hallFile; List<Hall> hallBeans = new
		 * CsvToBeanBuilder(new FileReader(hallFileName)).withType(Cinema.class).build()
		 * .parse();
		 */
	}

}
