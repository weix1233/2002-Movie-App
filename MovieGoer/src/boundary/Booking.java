package boundary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import entity.Cinema;
import entity.Hall;
import entity.MLDataObject;
import entity.MovieListing;
import entity.Ticket;
import entity.Ticket.ticketType;
import entity.User;

public class Booking {
	private String transactionID;
	private double totalPrice = 0.0;;
	private ArrayList<Ticket> tickets;
	private ticketType ticType;
	private List<User> users;
	private Hall hall;
	private MovieListing movieListing;


	//placeholders
	private ArrayList<Integer> rows;
	private ArrayList<Integer> cols;

	public Booking(Hall hall, MovieListing movieListing,List<User> users) throws IllegalStateException, FileNotFoundException {
		this.hall = hall;
		this.movieListing = movieListing;
		this.users = users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void displayBooking() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		Scanner sc = new Scanner(System.in);
		if (movieListing.getMovie().getShowingStatus().equals("END_OF_SHOWING")) {
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
		System.out.println("Please select your ticket type: ");
		System.out.println("1. Adult, 2. Senior, 3. Child");
		int selectedTicketType = sc.nextInt();
		switch (selectedTicketType) {
		case 1:
			ticType = ticketType.ADULT;
			break;
		case 2:
			ticType = ticketType.SENIOR;
			break;
		case 3:
			ticType = ticketType.CHILD;
			break;
		default:
			ticType = ticketType.ADULT;
			break;
		}
		System.out.println("=========================================");
		System.out.println("Seats selection: ");
		hall.showSeats();
		System.out.println("Please enter row & column of each seat: ");
		System.out.println("E.g.: A4 A5 = 14 15");
		String[] selectedSeats = sc.nextLine().split(" ");
		for (int i = 0; i < selectedSeats.length; i++) {
			int value = Integer.parseInt(selectedSeats[i]);
			int row = (value / 10) % 10;
			int col = (value / 1) % 10;
			rows.add(row);
			cols.add(col);
			Ticket newTicket = new Ticket(hall, movieListing, ticType, row, col);
			totalPrice += newTicket.getTicketPrice();
			tickets.add(newTicket);
		}
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
			//assumes every customer is a new user at first
			System.out.println("Are you a new user? Press Y for yes or N for no.");
			char choice2 = sc.next().charAt(0);
			if (choice2 == 'Y') {
				System.out.println("Please enter your name: ");
				String name = sc.next();
				System.out.println("Please enter your mobile number: ");
				int mobileNo = sc.nextInt();
				System.out.println("Please enter your email address: ");
				String email = sc.next();
				User newUser = new User(name, email, mobileNo);
				newUser.addBookingHistory(newBooking);
				users.add(newUser);
				this.writeBH();
			} else {
				if(users.isEmpty()) {
					System.out.println("You are not in the customer database.");
					System.out.println("Booking will be cancelled.");
					System.out.println("=========================================");
					return;
				}
				System.out.println("Please enter your mobileNo: ");
				int mobileNo2 = sc.nextInt();
				int valid=0;
				for(int i=0; i<users.size(); i++) {
					if(mobileNo2 == users.get(i).getMobileNo()) {
						users.get(i).addBookingHistory(newBooking);
						valid=1;
						this.writeBH();
					}
				}
				if(valid == 0) {
					System.out.println("You are not in the customer database.");
					System.out.println("Booking will be cancelled.");
					System.out.println("=========================================");
					return;
				}
			}	
			for (int i = 0; i < tickets.size(); i++) {
				hall.updateSeats(rows.get(i), cols.get(i));
				movieListing.getMovie().addSales();
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

	public void setTransactionID() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String dateTimeStr = dateTime.format(formatter);
		this.transactionID = Integer.toString(movieListing.getHallID()) + dateTimeStr;
	}

	public String getTransactionID() {
		return transactionID;
	}
   /**
     * Writes a list of users to the user csv
     * @param u List of users to write 
     * @throws IOException
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     */
    public void writeBH() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    	Writer writer = new FileWriter("C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv");
    	StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
    	beanToCsv.write(this.users);
    	writer.close();
    }
    /**
	 * Display the prices for a Standard cinema class
	 */
	public void showStandardPrices() {
		System.out.println("Current ticket prices for Standard cinema class: ");
		System.out.println("=================================================");
		System.out.println("Ticket type          2D Movies    3D Mvoies");
		System.out.println("Senior Citizens*     $5.00		  $7.00");
		System.out.println("Students**           $7.00		  $9.00");
		System.out.println("Mon - Thu	         $9.00		  $11.00");
		System.out.println("Fri (before 6pm)     $10.00		  $12.00");
		System.out.println("Fri (from 6pm)       $11.00		  $13.00");
		System.out.println("Sat & Sun 	         $11.00		  $13.00");
		System.out.println("Public Holidays      $13.00		  $15.00");
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
		System.out.println("Ticket type          2D Movies    3D Mvoies");
		System.out.println("Senior Citizens*     $7.00		  $9.00");
		System.out.println("Students**           $9.00		  $11.00");
		System.out.println("Mon - Thu	         $11.00		  $13.00");
		System.out.println("Fri (before 6pm)     $12.00		  $14.00");
		System.out.println("Fri (from 6pm)       $13.00		  $15.00");
		System.out.println("Sat & Sun 	         $13.00		  $15.00");
		System.out.println("Public Holidays      $15.00		  $17.00");
		System.out.println("=================================================");
		System.out.println("*For patrons 55 years && older, valid from Mon-Thu only.");
		System.out.println("**valid from Mon-Thu only");
	}

}

/*
	System.out.println("Please enter your name: ");
	String name = sc.next();
	System.out.println("Please enter your mobile number: ");
	int mobileNo = sc.nextInt();
	System.out.println("Please enter your email address: ");
	String email = sc.next();
	User newUser = new User(name, email, mobileNo);
	setTransactionID();
	for (int i = 0; i < tickets.size(); i++) {
		hall.updateSeats(rows.get(i), cols.get(i));
		movieListing.getMovie().addSales();
	}
	System.out.println("Your booking is successful!");
	System.out.println("Your transaction ID is : " + getTransactionID());
	System.out.println("=========================================");
*/
