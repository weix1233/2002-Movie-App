package boundary;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class ControlPanel {
	private static void WelcomeBanner() {
		System.out.println("Welcome to the movie admin console. You can edit movie information here");
	}

	public static void main(String[] args)
			throws IllegalStateException, FileNotFoundException, NoSuchAlgorithmException {
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
			break;
		case 2:
			// member
			//
			break;
		case 3:
			// admin
			AdminControl ac = new AdminControl();
			ac.MainMenu();
		}
	}

}
