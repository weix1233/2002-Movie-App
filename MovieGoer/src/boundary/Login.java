package boundary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.ReadCSVFiles;
import entity.User;

/**
 * Login system to authenticate users. All users except for guest, must
 * authenticate before being able to access the app.
 * 
 * @author SS4 Group 4
 *
 */
public class Login {
	/**
	 * Creates a empty Login object
	 */
	public Login() {
	}

	/**
	 * Returns a SHA-256 hash string. Used to hash a user's password to comply with
	 * security standards
	 * 
	 * @param password user's password String to be hashed
	 * @return SHA-256 hashed password String
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	/**
	 * Authentication user interface for users to log in to the app.
	 * 
	 * @return a integer to indicate the type of user logged in.
	 * @throws IllegalStateException
	 * @throws NoSuchAlgorithmException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public User validate() throws IllegalStateException, NoSuchAlgorithmException, CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Login options\n1. Guest\n2. Account\n3. Register as member");
		int choice;
		do {
			choice = sc.nextInt();
			if (choice < 1 || choice > 3) {
				System.out.println("---- Error! Choose only given options ----");
			}
		} while (choice < 1 || choice > 3);
		List<User> beans = ReadCSVFiles.getLoginDetail();
		User temp;
		switch (choice) {
		case 1:
			System.out.println("Welcome Guest!");
			temp = Register.createGuest(beans);
			return temp;
		case 3:
			temp = registerMember(beans);
			return temp;
		default:
			break;
		}
		// System.out.println(hashPassword("p4ssw0rd"));
		int loginAttempts = 0;
		sc.nextLine();
		do {
			System.out.print("Enter username: ");
			String user = sc.nextLine();
			System.out.print("Enter password: ");
			String pass = hashPassword(sc.nextLine());

			for (int i = 0; i < beans.size(); i++) {
				if (user.equals(beans.get(i).getUsername())) {
					if (pass.equals(beans.get(i).getPassword())) {
						System.out.println("---- Login success! ----");
						return beans.get(i);
					} else {
						System.out.println("---- Error! Login failure -----");
						loginAttempts++;
						break;
					}
				}
			}

		} while (loginAttempts < 3); // if fail to login 3 times, the validate method will be callled by ControlPanel

		return null;

	}

	/**
	 * Creates a new member account and saves it to the member database (csv file)
	 * 
	 * @param beans List of member details from member database
	 * @throws NoSuchAlgorithmException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private User registerMember(List<User> beans) throws NoSuchAlgorithmException, CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException, IllegalStateException, IOException {
		return Register.createMember(beans);
	}
}
