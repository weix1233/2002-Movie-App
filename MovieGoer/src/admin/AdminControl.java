package admin;

import java.util.Scanner;

public class AdminControl {
	Scanner sc = new Scanner(System.in);

	public void MainMenu() {
		System.out.print("Choose Cineplex\n(1) Jurong CV (2) Boon lay CV (3) Pioneer CV: ");
		int cineplex = sc.nextInt();
		switch (cineplex) {
		case 1:
			// Make jurong object
			break;
		case 2:
			// Make Boon lay object
			break;
		case 3:
			// Make Pioneer object
			break;
		}
		System.out.print("Choose cinema hall 1 ~ 5");
		int hall = sc.nextInt();

	}
}
