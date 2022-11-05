package admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.Cinema;
import movie.MovieListingControl;

public class AdminControl {
	Scanner sc = new Scanner(System.in);

	public void MainMenu() throws IllegalStateException, FileNotFoundException {
		System.out.print("\n(1) Access Cineplex database (2) Edit movie database: \n");
		int c = 1;//sc.nextInt();
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
	
	private void getCinemaInfo(List<Cinema> beans, int c) {
		System.out.printf("Name: %s\n", beans.get(c).getName());
		for(int j = 0;j < beans.get(c).getHallID().size(); j++) {
			System.out.println("Hall " + beans.get(c).getHallID().get(j));
			System.out.println("Available Show Times: " + beans.get(c).getsAST(j));
			System.out.println("Show Times: " + beans.get(c).getST(j));
		}

	}

	public void MovieMenu() throws IllegalStateException, FileNotFoundException {
		String cinemaFileName = "C:\\Users\\hue\\Desktop\\database\\cinema\\cinema.csv";
		List<Cinema> cinemaBeans = new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build()
				.parse();
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		getCinemaInfo(cinemaBeans, locationID);
		System.out.print("Selection option\n(1) Show all current movie listings (2) Modify a cinema hall movie listing: ");
		int choice = sc.nextInt();
		
		System.out.print("Select cinema hall number (1 ~ 3): ");
		int hallID = sc.nextInt();
		
		System.out.println("Select option\n(1) Add movie listing (2) Remove movie listing (3) Update movie listing (4) List current movie listing: ");
		MovieListingControl mc = new MovieListingControl();
		int option = sc.nextInt();
		
		
		
		
	}
}
