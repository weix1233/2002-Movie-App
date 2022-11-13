package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.WriteCSVFiles;

public class Register {
	protected static void createMember(List<Login> beans) throws NoSuchAlgorithmException, CsvDataTypeMismatchException,
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
		Login newMember = new Login(username, hashedPassword, name, mobileNo, email);
		beans.add(newMember);
		WriteCSVFiles.loginWriter(beans);
	}
}
