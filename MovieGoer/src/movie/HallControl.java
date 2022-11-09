package movie;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import movie.MovieListing.dayOfWeek;
import movie.MovieListing.screenType;

public class HallControl {
	Scanner sc = new Scanner(System.in);

	public void hallListAllMovieListing(List<MovieListing> beans, Hall hall) {
		for (int i = 0; i < beans.size(); i++) {
			beans.get(i).printListing();
		}
	}

	public void hallAddMovieListing(List<Movie> beans, Hall hall) {
		MovieListingControl mc = new MovieListingControl();
		System.out.println("\nChoose movie to add (Number): ");
		int moviePos = sc.nextInt();
		Movie selectedMovie = beans.get(moviePos);
		screenType screen = mc.chooseScreenType();
		dayOfWeek day = mc.chooseDay();
		String strDay = day.name();
		List<String> availableTiming = hall.getAvailableShowTimes();
		System.out.println("\nChoose available showing time");
		for (int i = 0; i < availableTiming.size(); i++) {
			if (availableTiming.get(i).split(" ")[0].equals(strDay)) {
				System.out.println(Integer.toString(i) + ". " + availableTiming.get(i));
			}
		}
		int showTimePos = sc.nextInt();
		hall.addMovieListing(selectedMovie, screen, day, showTimePos);
	}

	public void hallDelMovieListing(Hall hall, List<MovieListing> hallML) {
		if (hallML.size() == 0) {
			System.out.println("No current movie listings to remove");
			return;
		}
		System.out.println("Current movie listings for hall " + Integer.toString(hall.getHallID()));
		for (int i = 0; i < hallML.size(); i++) {
			System.out.println(Integer.toString(i) + ". " + hallML.get(i).getMovie().getMovieTitle() + " "
					+ hallML.get(i).getShowtime());
		}
		System.out.print("Select listing to remove: ");
		int listPos = sc.nextInt();
		hall.delMovieListing(listPos);
	}

	public void hallUpdateMovieListing(Hall hall, List<MovieListing> hallML, MovieListingControl mc) {
		if (hallML.size() == 0) {
			System.out.println("No current movie listings to update");
			return;
		}
		System.out.println("Current movie listings for hall " + Integer.toString(hall.getHallID()));
		System.out.println("Title \t|  Screen Type  |  Show Time  |  Age Rating");
		for (int i = 0; i < hallML.size(); i++) {
			System.out.print(Integer.toString(i) + ". ");
			hallML.get(i).printListing();
		}
		System.out.println("Select listing to update");
		int movieListPosition = sc.nextInt();
		System.out.print("Select option\n(1) Edit screen type (2) Edit showing time (3) Exit: ");
		int editChoice = sc.nextInt();
		switch (editChoice) {
		case 1:
			hallML.get(movieListPosition).setType(mc.chooseScreenType());
			break;
		case 2:
			String showDay = mc.chooseDay().name();
			List<String> availableShowTimes = hall.getAvailableShowTimes();
			List<String> currentShowTimes = hall.getShowTimes();
			System.out.println("\nChoose available showing time");
			for (int i = 0; i < availableShowTimes.size(); i++) {
				if (availableShowTimes.get(i).split(" ")[0].equals(showDay)) {
					System.out.println(Integer.toString(i) + ". " + availableShowTimes.get(i));
				}
			}
			int showTimePos = sc.nextInt();
			String tempShowTime = availableShowTimes.remove(showTimePos);
			currentShowTimes.add(tempShowTime);
			String removeShowTime = hallML.get(movieListPosition).getShowtime();
			hallML.get(movieListPosition).setShowtime(tempShowTime);
			currentShowTimes.remove(removeShowTime);
			availableShowTimes.add(removeShowTime);
			Collections.sort(availableShowTimes);
			break;
		default:
			break;
		}
	}
}
