package movie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import movie.Ticket.ticketType;

public class Booking {
	private String transactionID;
	private double totalPrice = 0.0;;
	private ArrayList<Ticket> tickets;
	private Hall hall;
	private MovieListing movieListing;
	private Cinema cinema;
	private ticketType ticType;

	// placeholders
	private Ticket ticket;
	private ArrayList<Integer> rows;
	private ArrayList<Integer> cols;

	public Booking(Hall hall, Cinema cinema) {
		this.hall = hall;
		this.cinema = cinema;
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
		System.out.println("Please select your seats by entering the row and column: ");
		System.out.println("E.g.: A4 = 14");
		String[] selectedSeats = sc.nextLine().split(" ");
		for (int i = 0; i < selectedSeats.length; i++) {
			int value = Integer.parseInt(selectedSeats[i]);
			int row = (value / 10) % 10;
			int col = (value / 1) % 10;
			rows.add(row);
			cols.add(col);
			Ticket newTicket = new Ticket(ticType, row, col);
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
