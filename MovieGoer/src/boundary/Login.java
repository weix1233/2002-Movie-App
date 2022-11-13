package boundary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

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
		System.out.println("Login options\n1. Guest\n2. Member\n3. Admin\n");
		int choice = sc.nextInt();
		String loginFile;
		switch (choice) {
		case 1:
			System.out.println("Welcome Guest!");
			return choice;
		case 2:
			loginFile = "member.txt";
			break;
		case 3:
			loginFile = "admin.txt";
			break;
		default:
			System.out.println("Invalid input, defaulting to Guest!");
			return 1;
		}
		// where the login credentials are stored. May need to modify based on where you
		// store the files
		String loginFilePath = "C:\\Users\\Valen\\git\\2002-Movie-App\\MovieGoer\\login\\" + loginFile;

		int loginAttempts = 0;
		do {
			System.out.print("Enter username: ");
			String user = sc.next();
			System.out.print("Enter password: ");
			String pass = hashPassword(sc.next());
				List<Login> beans = new CsvToBeanBuilder(new FileReader(loginFilePath)).withType(Login.class)
						.build().parse();
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
