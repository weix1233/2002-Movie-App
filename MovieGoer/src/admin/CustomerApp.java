package admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import com.opencsv.bean.CsvToBeanBuilder;

import movie.Booking;
import movie.Cinema;
import movie.Hall;
import movie.MovieListing;
import movie.User;

public class CustomerApp {
	private Cinema currentCinema;
	public CustomerApp() throws IllegalStateException, FileNotFoundException {
		this.currentCinema = chooseCinema();
		}
	public Cinema chooseCinema() throws IllegalStateException, FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		String cinemaFileName = "C:\\Users\\Valen\\git\\2002-Movie-App\\MovieGoer\\database\\cinema\\cinema.csv";
		List<Cinema> cinemaBeans = new CsvToBeanBuilder(new FileReader(cinemaFileName)).withType(Cinema.class).build()
				.parse();
		System.out.print("Select location\n(1) jurong (2) orchard (3) yishun: ");
		int locationID = sc.nextInt();
		return cinemaBeans.get(locationID);
	}
	public void displayMenu() {
		int count = (50 - currentCinema.getName().length() - 9)/2;
		String buf = "";
		for(int i = 0;i < count;i++) buf = buf + "=";
		System.out.println("==================================================");
		if(currentCinema.getName().length() % 2 == 0) System.out.print("=");
		System.out.printf("%s Cathay %s %s\n",buf,currentCinema.getName(),buf);
		System.out.println("====== 1. List Movie                        ======");
		System.out.println("====== 2. View Movie Details                ======");
		System.out.println("====== 3. Check Seats                       ======");
		System.out.println("====== 4. View Booking history              ======");
		System.out.println("====== 5. View Top 5 Movies                 ======");
		System.out.println("==================================================");
	}
	public void bookMovie(int hallID,MovieListing ml){
		Hall h = currentCinema.getHall(hallID);
		Booking b = new Booking(h,currentCinema,ml);
		b.displayBooking();
	}
	public void bookingHistory() throws IllegalStateException, FileNotFoundException {
		boolean check = false;
		String name = null;
		String email = null;
		int mobileNo = 0;
		int attempt = 0;
		String filePath = "C:\\Users\\Valen\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv";
		List<User> userBeans = new CsvToBeanBuilder(new FileReader(filePath)).withType(User.class).build().parse();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter your credentials");
			System.out.println("Name: ");
			name = sc.next();
			System.out.println("Email: ");
			email = sc.next();
			System.out.println("Mobile No: ");
			mobileNo = sc.nextInt();
			System.out.println("Checking ...");
			// System.out.printf("Your credentials\nName: %s\nEmail: %s\nMobile No:%d\n", name,email,mobileNo);
			for(int i = 0;i < userBeans.size();i++) {
				/* Sanity Check
				 * System.out.printf("Current data (%d)\nName: %s\nEmail: %s\nMobile No:%d\n", 
				 * i,
				 * userBeans.get(i).getName(),
				 * userBeans.get(i).getEmail(),
				 * userBeans.get(i).getMobileNo());
				 */
				if(userBeans.get(i).getName().equals(name)) {
					if(userBeans.get(i).getEmail().equals(email)) {
						if(userBeans.get(i).getMobileNo() == mobileNo) {
							System.out.println(userBeans.get(i).getbookingHistory());
							check = true;
							break;
						}
					}
				}
			}
			if(check == true) {
				break;	
			}
			System.out.printf("Check failed...\nAttempts left: %d\n",3 - ++attempt);
		} while(attempt < 3);
	}
}
