package admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

public class ControlPanel {
	// private MovieListing[] listing;

	private static void WelcomeBanner() {
		System.out.println("Welcome to the movie admin console. You can edit movie information here");
	}

	public static void main(String[] args) throws IllegalStateException, FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter username: ");
		String user = sc.next();
		System.out.print("Enter password: ");
		String pass = sc.next();
		List<StaffLogin> beans;

		beans = new CsvToBeanBuilder(new FileReader("C:\\Users\\tanju\\Desktop\\my.txt")).withType(StaffLogin.class)
				.build().parse();
		if (user.equals(beans.get(0).getUsername()) && pass.equals(beans.get(0).getPassword())) {
			System.out.println("Login success");
		} else {
			System.out.println("Login fail");
		}
	}

}
