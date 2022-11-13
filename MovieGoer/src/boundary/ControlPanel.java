package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * The start point of MOBLIMA App. This decides if the user is accessing the
 * customer or admin user interface
 * 
 * @author SS4 Group 4
 *
 */
public class ControlPanel {
	/**
	 * A welcome banner displayed to all users who connect to the app.
	 *
	 */
	private static void WelcomeBanner() {
		System.out.println("---- Welcome to MOBLIMA! ----");
	}

	/**
	 * main
	 *
	 */
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
