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

import control.ReadCSVFiles;
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
	 * The user that is currently logged in
	 */
	private User user;
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
	 * @param u        list of users in the user database
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public Booking(Hall hall, MovieListing movieListing, User u)
			throws IllegalStateException, FileNotFoundException {
		this.hall = hall;
		this.movieListing = movieListing;
		this.user = u;
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
		users = ReadCSVFiles.getLoginDetail();
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
			user = ReadCSVFiles.findUser(users,user);
			setTransactionID();
			String newBooking = movieListing.getMovie().getMovieTitle() + getTransactionID();
			user.addBookingHistory(newBooking);
			WriteCSVFiles.userToCSV(users);
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
	 * @throws FileNotFoundException 
	 * @throws IllegalStateException 
	 */
	public void showStandardPrices() throws IllegalStateException, FileNotFoundException {
		double basePrice = ReadCSVFiles.getOptions().get(0).getBasePrice() + 2.0;
		double twoDBP = basePrice + 2.0;
		double threeDBP = basePrice + 4.0;
		System.out.println("Current ticket prices for Standard cinema class: ");
		System.out.println("=================================================");
		System.out.println("Ticket type          2D Movies      3D Movies");
		System.out.printf("Senior Citizens*     $%.2f          $%.2f\n",twoDBP - 4.0,threeDBP - 4.0);
		System.out.printf("Students**           $%.2f          $%.2f\n",twoDBP - 2.0,threeDBP - 2.0);
		System.out.printf("Mon - Thu            $%.2f         $%.2f\n",twoDBP,threeDBP);
		System.out.printf("Fri (before 6pm)     $%.2f         $%.2f\n",twoDBP + 1.0,threeDBP + 1.0);
		System.out.printf("Fri (from 6pm)       $%.2f         $%.2f\n",twoDBP + 2.0,threeDBP + 2.0);
		System.out.printf("Sat & Sun            $%.2f         $%.2f\n",twoDBP + 2.0,threeDBP + 2.0);
		System.out.printf("Public Holidays      $%.2f         $%.2f\n",twoDBP + 4.0,threeDBP + 4.0);
		System.out.println("=================================================");
		System.out.println("*For patrons 55 years && older, valid from Mon-Thu only.");
		System.out.println("**valid from Mon-Thu only");
	}

	/**
	 * Display the prices for a Platinum cinema class
	 * @throws FileNotFoundException 
	 * @throws IllegalStateException 
	 */
	public void showPlatiumPrices() throws IllegalStateException, FileNotFoundException {
		double basePrice = ReadCSVFiles.getOptions().get(0).getBasePrice() + 4.0;
		double twoDBP = basePrice + 2.0;
		double threeDBP = basePrice + 4.0;
		System.out.println("Current ticket prices for Platium cinema class: ");
		System.out.println("=================================================");
		System.out.println("Ticket type          2D Movies      3D Movies");
		System.out.printf("Senior Citizens*     $%.2f          $%.2f\n",twoDBP - 4.0,threeDBP - 4.0);
		System.out.printf("Students**           $%.2f          $%.2f\n",twoDBP - 2.0,threeDBP - 2.0);
		System.out.printf("Mon - Thu            $%.2f         $%.2f\n",twoDBP,threeDBP);
		System.out.printf("Fri (before 6pm)     $%.2f         $%.2f\n",twoDBP + 1.0,threeDBP + 1.0);
		System.out.printf("Fri (from 6pm)       $%.2f         $%.2f\n",twoDBP + 2.0,threeDBP + 2.0);
		System.out.printf("Sat & Sun            $%.2f         $%.2f\n",twoDBP + 2.0,threeDBP + 2.0);
		System.out.printf("Public Holidays      $%.2f         $%.2f\n",twoDBP + 4.0,threeDBP + 4.0);
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