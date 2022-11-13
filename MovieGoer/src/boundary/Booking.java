package boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import control.WriteCSVFiles;
import entity.Hall;
import entity.MovieListing;
import entity.Ticket;
import entity.Ticket.ticketType;
import entity.User;

/**
 * Allows the user to book their movie of choice based on the movieListing
 * 
 * @author SS4 Group 4
 *
 */
public class Booking {
	/**
	 * Scanner Object
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * ID of the transaction
	 */
	private String transactionID;
	/**
	 * Price of the tickets
	 */
	private double totalPrice = 0.0;;
	/**
	 * An array of tickets depending on how many seats they chose
	 */
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	/**
	 * The type of ticket
	 */
	private ticketType ticType;
	/**
	 * List of users to check if users exist
	 */
	private List<User> users;
	/**
	 * The hall for the chosen movieListing
	 */
	private Hall hall;
	/**
	 * The chosen movieListing
	 */
	private MovieListing movieListing;

	// placeholders
	/**
	 * The row for the seats chosen
	 */
	private ArrayList<Integer> rows = new ArrayList<Integer>();
	/**
	 * The columns for seats chosen
	 */
	private ArrayList<Integer> cols = new ArrayList<Integer>();

	/**
	 * Constructs a
	 * 
	 * @param hall         the Hall associated with the current movieListing
	 * @param movieListing customer's choice of movieListing
	 * @param users        list of users in the user database
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public Booking(Hall hall, MovieListing movieListing, List<User> users)
			throws IllegalStateException, FileNotFoundException {
		this.hall = hall;
		this.movieListing = movieListing;
		this.users = users;
	}

	/**
	 * Mutator to change the list of users
	 * 
	 * @param users List of users in the database
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Shows the UI for booking movies as well as the seats for the respective
	 * movie.
	 * Writes the booking history after transaction is completed as well
	 * 
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 */
	public void displayBooking() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		
		if (!(movieListing.getMovie().getShowingStatus().equals("NOW_SHOWING") || movieListing.getMovie().getShowingStatus().equals("PREVIEW"))) {
			System.out.println("Movie is not available for showing.");
			return;
		}
		System.out.println("=========================================");
		System.out.println("Ticket prices: ");
		if (hall.getIP() == false)
			this.showStandardPrices();
		else
			this.showPlatiumPrices();
		System.out.println("=========================================");
		System.out.println("Seats selection: ");
		hall.showSeats(this.movieListing.getSeats());
		String[] selectedSeats;
		boolean check2 = false;
		do {
			System.out.println("Please enter row & column of each seat: ");
			System.out.println("E.g.: A4 A5 = 14 15");
			selectedSeats = sc.nextLine().split(" ");
			for (int i = 0; i < selectedSeats.length; i++) {
				int value = Integer.parseInt(selectedSeats[i]);
				int row = (value / 10) % 10;
				int col = (value / 1) % 10;
				rows.add(row);
				cols.add(col);
				ticType = this.selectTicketType();
				sc.nextLine();
				Ticket newTicket = new Ticket(hall, movieListing, ticType, row, col);
				totalPrice += newTicket.getTicketPrice();
				tickets.add(newTicket);
				if (!hall.checkSeats(row, col)) {
					System.out.println("Seat is already taken!");
					System.out.println("Please select seats again.");
					rows.clear();
					cols.clear();
					tickets.clear();
					totalPrice = 0;
					break;
				}
				check2 = true;
			}
		} while (check2 == false);
		System.out.println("=========================================");
		
		System.out.println("Confirmation of Booking: ");
		System.out.println("Movie Details: ");
		movieListing.printListing();
		System.out.println("Seats selected: ");
		System.out.println(Arrays.toString(selectedSeats));
		System.out.printf("Total price is %.2f (inclusive of GST)\n", totalPrice);
		System.out.println("Confirm to book? Press Y for yes or N for no.");
		char choice = sc.next().charAt(0);
		if (choice == 'Y') {
			setTransactionID();
			String newBooking = movieListing.getMovie().getMovieTitle() + getTransactionID();
			// assumes every customer is a new user at first'
			System.out.println("Are you a new user? Press Y for yes or N for no.");
			char choice2 = sc.next().charAt(0);
			sc.nextLine();
			if (choice2 == 'Y') {
				System.out.println("Please enter your name: ");
				String name = sc.nextLine();
				System.out.println("Please enter your mobile number: ");
				int mobileNo = sc.nextInt();
				System.out.println("Please enter your email address: ");
				sc.nextLine();
				String email = sc.nextLine();
				User newUser = new User(name, email, mobileNo, false, null, null);
				newUser.addBookingHistory(newBooking);
				users.add(newUser);
				WriteCSVFiles.userToCSV(users);
			} else {
				if (users.isEmpty()) {
					System.out.println("You are not in the customer database.");
					System.out.println("Booking will be cancelled.");
					System.out.println("=========================================");
					return;
				}
				System.out.println("Please enter your mobileNo: ");
				int mobileNo2 = sc.nextInt();
				int valid = 0;
				for (int i = 0; i < users.size(); i++) {
					if (mobileNo2 == users.get(i).getMobileNo()) {
						users.get(i).addBookingHistory(newBooking);
						valid = 1;
						WriteCSVFiles.userToCSV(users);
					}
				}
				if (valid == 0) {
					System.out.println("You are not in the customer database.");
					System.out.println("Booking will be cancelled.");
					System.out.println("=========================================");
					return;
				}
			}
			for (int i = 0; i < tickets.size(); i++) {
				hall.updateSeats(rows.get(i), cols.get(i));
				movieListing.getMovie().addSales();
				movieListing.updateSeat(rows.get(i)-1,cols.get(i)-1);
			}
			System.out.println("Your booking is successful!");
			System.out.println("Your transaction ID is : " + getTransactionID());
			System.out.println("=========================================");
		} else {
			System.out.println("Your booking has been cancelled.");
			System.out.println("=========================================");
		}
		rows.clear();
		cols.clear();
		tickets.clear();
	}

	/**
	 * Changes the transaction ID
	 */
	public void setTransactionID() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String dateTimeStr = dateTime.format(formatter);
		this.transactionID = Integer.toString(movieListing.getHallID()) + dateTimeStr;
	}

	/**
	 * Gets transaction ID
	 * 
	 * @return Transaction ID
	 */
	public String getTransactionID() {
		return transactionID;
	}
	/**
	 * Display the prices for a Standard cinema class
	 */
	public void showStandardPrices() {
		System.out.println("Current ticket prices for Standard cinema class: ");
		System.out.println("=================================================");
		System.out.println("Ticket type          2D Movies      3D Movies");
		System.out.println("Senior Citizens*     $5.00          $7.00");
		System.out.println("Students**           $7.00          $9.00");
		System.out.println("Mon - Thu            $9.00          $11.00");
		System.out.println("Fri (before 6pm)     $10.00         $12.00");
		System.out.println("Fri (from 6pm)       $11.00         $13.00");
		System.out.println("Sat & Sun            $11.00         $13.00");
		System.out.println("Public Holidays      $13.00         $15.00");
		System.out.println("=================================================");
		System.out.println("*For patrons 55 years && older, valid from Mon-Thu only.");
		System.out.println("**valid from Mon-Thu only");
	}

	/**
	 * Display the prices for a Platinum cinema class
	 */
	public void showPlatiumPrices() {
		System.out.println("Current ticket prices for Platium cinema class: ");
		System.out.println("=================================================");
		System.out.println("Ticket type          2D Movies      3D Movies");
		System.out.println("Senior Citizens*     $7.00          $9.00");
		System.out.println("Students**           $9.00          $11.00");
		System.out.println("Mon - Thu            $11.00         $13.00");
		System.out.println("Fri (before 6pm)     $12.00         $14.00");
		System.out.println("Fri (from 6pm)       $13.00         $15.00");
		System.out.println("Sat & Sun            $13.00         $15.00");
		System.out.println("Public Holidays      $15.00         $17.00");
		System.out.println("=================================================");
		System.out.println("*For patrons 55 years && older, valid from Mon-Thu only.");
		System.out.println("**valid from Mon-Thu only");
	}
	/**
	 * Allow the user to choose each ticket type
	 * @return ticket type
	 */
	public ticketType selectTicketType() {
		System.out.println("=========================================");
		System.out.println("Please select your ticket type: ");
		System.out.println("1. Adult, 2. Senior, 3. Child");
		int selectedTicketType = sc.nextInt();
		switch (selectedTicketType) {
			case 1:
				return ticketType.ADULT;
			case 2:
				return ticketType.SENIOR;
			case 3:
				return ticketType.CHILD;
			default:
				return ticketType.ADULT;
		}
	}
}