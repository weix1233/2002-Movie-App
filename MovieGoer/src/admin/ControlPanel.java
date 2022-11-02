package admin;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class ControlPanel {
	// private MovieListing[] listing;

	private static void WelcomeBanner() {
		System.out.println("Welcome to the movie admin console. You can edit movie information here");
	}

	public static void main(String[] args)
			throws IllegalStateException, FileNotFoundException, NoSuchAlgorithmException {
		WelcomeBanner();
		StaffLogin sl = new StaffLogin();
		int userType = 0;
		// Guest = 1, Members = 2, Admin = 3, Login Errors = -1
		do {
			userType = sl.validate();
		} while (userType == -1);
	}

}
