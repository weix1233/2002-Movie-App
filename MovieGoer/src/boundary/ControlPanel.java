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
		StaffLogin sl = new StaffLogin();
		int userType = 0;
		// Guest = 1, Members = 2, Admin = 3, Login Error = -1 user is forced to login
		// again
		do {
			// userType = sl.validate();
			userType = 3;
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
			/*
			 * MovieListingControl mc = new MovieListingControl(); List<Movie> beans =
			 * mc.listMovieTitles(); System.out.println("Adding a movie listing"); mls = new
			 * ArrayList<MovieListing>(); System.out.println();
			 * mls.add(mc.createMovieListing(beans, mls.size()));
			 * mls.add(mc.createMovieListing(beans, mls.size()));
			 * 
			 * System.out.println("Available movie listings"); System.out
			 * .println("Movie Title | Screen Type | Showing Status | Age Rating | Day | Show Time | Cinema Hall"
			 * ); for (int i = 0; i < mls.size(); i++) { mls.get(i).printListing(); }
			 * 
			 * System.out.println("Update movie listings"); mc.updateMovieListing(mls, 0);
			 * mc.updateMovieListing(mls, 1);
			 * 
			 * for (int i = 0; i < mls.size(); i++) { mls.get(i).printListing(); } break;
			 */
		}
	}

}
