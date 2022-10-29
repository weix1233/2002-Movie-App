package admin;

import java.util.Scanner;

import movie.MovieListing;

public class ControlPanel {
	private MovieListing[] listing;

	private void WelcomeBanner() {
		System.out.println("Welcome to the movie admin console. You can edit movie information here");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StaffLogin sl = new StaffLogin();
		String username = "";
		String password = "";
		Boolean login = false;
		do {
			System.out.print("Enter username: ");
			username = sc.next();
			System.out.print("Enter password: ");
			password = sc.next();
			login = sl.validate(username, password);
		} while (login == false);

		int choice = 0;
		while (true) {
			System.out.println(
					"Available options\n1. Modify movie listings \n2. Modify cinema showtimes\n3. Modify screening movies\n");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println(
						"Available options\n1. Create new movie listing\n2. Update current movie listing\n3. Remove a movie listing\n");
				choice = sc.nextInt();
				switch (choice) {

				}
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}

	}
}
