package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.WriteCSVFiles;
import entity.User;

public class Register {
	/**
	 * Create a new user object and add to the user database (csv file)
	 * 
	 * @param beans current list of members extracted from user database
	 * @return the user object created
	 * @throws NoSuchAlgorithmException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	protected static User createMember(List<User> beans) throws NoSuchAlgorithmException, CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException, IllegalStateException, IOException {
		Scanner sc = new Scanner(System.in);
		String username;
		int check;
		do {
			check = 0;
			System.out.print("Enter username: ");
			username = sc.next();
			for (int i = 0; i < beans.size(); i++) {
				if (beans.get(i).getUsername().equals(username)) {
					check = 1;
					System.out.println("---- Error! Username in use, please change ----");
				}
			}
		} while (check == 1);
		String password;
		do {
			check = 0;
			System.out.print("Enter password: ");
			password = sc.next();
			if (password.length() < 8) {
				check = 1;
				System.out.println("--- Error! Password length < 8, please change ----");
			}
		} while (check == 1);
		String hashedPassword = Login.hashPassword(password);
		sc.nextLine();
		System.out.print("Enter name: ");
		String name = sc.nextLine();
		System.out.print("Enter mobileNo: ");
		int mobileNo = sc.nextInt();
		String email;
		do {
			check = 0;
			System.out.print("Enter email: ");
			email = sc.next();
			if (email.indexOf("@") == -1) {
				check = 1;
				System.out.println("---- Error! Please use correct email format xxx@xxx.com ----");
			}
		} while (check == 1);
		// Login newMember = new Login(username, hashedPassword, name, mobileNo, email);
		User newUser = new User(name, email, mobileNo, false, username, hashedPassword);
		beans.add(newUser);
		WriteCSVFiles.userToCSV(beans);
		return newUser;
	}

	/**
	 * Creates a guest account and add to the user database. Guests have no username
	 * and password information
	 * 
	 * @param beans list of user objects extracted from user database
	 * @return newly created user object
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	protected static User createGuest(List<User> beans)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalStateException, IOException {
		Scanner sc = new Scanner(System.in);
		int check;
		System.out.print("Enter name: ");
		String name = sc.nextLine();
		System.out.print("Enter mobileNo: ");
		int mobileNo = sc.nextInt();
		sc.nextLine();
		String email;
		do {
			check = 0;
			System.out.print("Enter email: ");
			email = sc.next();
			if (email.indexOf("@") == -1) {
				check = 1;
				System.out.println("---- Error! Please use correct email format xxx@xxx.com ----");
			}
		} while (check == 1);
		// Login newMember = new Login(username, hashedPassword, name, mobileNo, email);
		User newUser = new User(name, email, mobileNo, false, null, null);
		beans.add(newUser);
		WriteCSVFiles.userToCSV(beans);
		return newUser;
	}
}
