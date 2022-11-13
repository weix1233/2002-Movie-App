package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ControlPanel {
	private static void WelcomeBanner() {
		System.out.println("Welcome to the movie admin console. You can edit movie information here");
	}

	public static void main(String[] args) throws IllegalStateException, NoSuchAlgorithmException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		WelcomeBanner();
		Login login = new Login();
		int userType = 0;
		// Guest = 1, Members = 2, Admin = 3, Login Error = -1 user is forced to login
		// again
		do {
			userType = login.validate();
			// userType = 3;
		} while (userType == -1);

		switch (userType) {
		case 1:
			// guest
			System.out.println("guest logged in");
			break;
		case 2:
			// member
			System.out.println("member logged in");
			break;
		case 3:
			// admin
			AdminControl ac = new AdminControl();
			ac.MainMenu();
		}
	}

}
