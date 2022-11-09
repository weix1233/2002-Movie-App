package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import entity.Cinema;
import entity.Hall;
import entity.MovieListing;
import entity.Ticket;
import entity.User;
import entity.Ticket.ticketType;

public class Booking {
	private String transactionID;
	private double totalPrice = 0.0;;
	private ArrayList<Ticket> tickets;
	private ticketType ticType;
	private ArrayList<User> users;
	private Hall hall;
	private MovieListing movieListing;
	private Cinema cinema;


	//placeholders
	private Ticket ticket;
	private ArrayList<Integer> rows;
	private ArrayList<Integer> cols;

	public Booking(Hall hall, Cinema cinema, MovieListing movieListing) {
		this.hall = hall;
		this.cinema = cinema;
		this.movieListing = movieListing;
	}

	public void displayBooking() {
		Scanner sc = new Scanner(System.in);
		if (movieListing.getMovie().getShowingStatus().equals("END_OF_SHOWING")) {
			System.out.println("Movie is not available for showing.");
			return;
		}
		System.out.println("=========================================");
		System.out.println("Ticket prices: ");
		if (cinema.getIP() == false)
			ticket.showStandardPrices();
		else
			ticket.showPlatiumPrices();
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
			Ticket newTicket = new Ticket(cinema, movieListing, ticType, row, col);
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
