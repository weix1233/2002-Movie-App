package admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StaffLogin implements Login {
	public Boolean validate(String username, String password) {
		Scanner sc;
		try {
			// Location of the login credentials
			sc = new Scanner(new File("C:\\Users\\tanju\\Desktop\\login.csv"));
			// comma is the delimiter pattern, since we use csv
			sc.useDelimiter(",");
			String user = sc.next().trim();
			String pass = sc.next().trim();
			if (user.equals(username) && pass.equals(password)) {
				System.out.println("Login successful");
				return true;
			}
			System.out.println("Login failed");
			return false;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

}
