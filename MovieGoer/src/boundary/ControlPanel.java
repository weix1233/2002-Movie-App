package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.ReadCSVFiles;
import entity.User;

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
		User userType = null;
		// Guest = 1, Members = 2, Admin = 3, Login Error = -1 user is forced to login
		// again
		do {
			userType = login.validate();
			// userType = 3;
		} while (userType == null);
		if(userType.getIsAdmin()) {
			System.out.println("admin logged in");
			AdminControl ac = new AdminControl();
			ac.MainMenu();
		}
		else {
			CustomerApp ca = new CustomerApp(userType);
			ca.displayMenu();
		}
	}

}
