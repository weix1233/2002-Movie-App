package boundary;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindByName;

import control.ReadCSVFiles;

public class Login {
	@CsvBindByName
	private String username;
	@CsvBindByName
	private String password;
	@CsvBindByName
	private String name;
	@CsvBindByName
	private int mobileNo;
	@CsvBindByName
	private String email;

	// hashing algorithm with SHA256. Returns a String (hashed)
	private static String hashPassword(String password) throws NoSuchAlgorithmException {
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

	// Login method to the Cinema system
	public int validate() throws IllegalStateException, FileNotFoundException, NoSuchAlgorithmException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Login options\n1. Guest\n2. Member\n3. Admin\n4. Register as member");
		int choice = sc.nextInt();
		List<Login> beans;
		switch (choice) {
		case 1:
			System.out.println("Welcome Guest!");
			return choice;
		case 2:
			beans = ReadCSVFiles.getLoginDetail("member.csv");
			break;
		case 3:
			beans = ReadCSVFiles.getLoginDetail("admin.csv");
			break;
		case 4:
			beans = ReadCSVFiles.getLoginDetail("member.csv");
			registerMember(beans);
			return -1;
		default:
			System.out.println("Invalid input, defaulting to Guest!");
			return 1;
		}
		// where the login credentials are stored. May need to modify based on where you
		// store the files
		System.out.println(hashPassword("pass"));
		int loginAttempts = 0;
		sc.nextLine();
		do {
			System.out.print("Enter username: ");
			String user = sc.nextLine();
			System.out.print("Enter password: ");
			String pass = hashPassword(sc.nextLine());

			if (user.equals(beans.get(0).getUsername()) && pass.equals(beans.get(0).getPassword())) {
				System.out.println("Login success");
				return choice;
			} else {
				System.out.println("Login failed, try again");
				loginAttempts++;
			}
		} while (loginAttempts < 3); // if fail to login 3 times, the validate method will be callled by ControlPanel

		return -1;

	}

	private void registerMember(List<Login> beans)
			throws IllegalStateException, FileNotFoundException, NoSuchAlgorithmException {
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
		String hashedPassword = hashPassword(password);
		System.out.print("Enter name: ");
		String name = sc.next();
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

	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
