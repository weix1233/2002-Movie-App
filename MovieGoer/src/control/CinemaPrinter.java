package control;

import java.util.List;

import entity.Cinema;
/**
 * Prints out a cinema's information
 * @author SS4 Group 4
 *
 */
public class CinemaPrinter {

	/**
	 * Formats and print the cinema information given its index position. Includes
	 * the cinema ID and number of halls available.
	 * @param beans List of cinemas extracted from cinema database
	 * @param c Chosen cinema's index position
	 */
	public void getCinemaInfo(List<Cinema> beans, int c) {
		System.out.printf("Cinema ID: %s\n", beans.get(c).getCinemaID());
		System.out.printf("Name: %s\n", beans.get(c).getName());
		for (int i = 1; i < beans.get(c).getHalls().size(); i++) {
			System.out.println("Hall " + beans.get(c).getHalls().get(i).getHallID());
		}
	}

}
