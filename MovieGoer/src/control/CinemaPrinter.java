package control;

import java.util.List;

import entity.Cinema;

public class CinemaPrinter {

	public void getCinemaInfo(List<Cinema> beans, int c) {
		System.out.printf("Cinema ID: %s\n", beans.get(c).getCinemaID());
		System.out.printf("Name: %s\n", beans.get(c).getName());
		for (int i = 1; i < beans.get(c).getHalls().size(); i++) {
			System.out.println("Hall " + beans.get(c).getHalls().get(i).getHallID());
		}
	}

}
