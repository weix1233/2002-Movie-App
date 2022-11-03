package movie;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Booking{
	private String transactionId;
	private double totalPrice = 0.0;;
	private Ticket ticket;
	private MovieListing movieListing;
	
	public Booking(String transactionId, double totalPrice, Ticket ticket, MovieListing movieListing) {
		this.transactionId = transactionId;
		this.totalPrice = totalPrice;
		this.ticket = ticket;
		this.movieListing = movieListing;
	}
	
	public void displayBooking() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=========================================");
    	System.out.println("Confirmation of Booking:");
    	movieListing.printListing();
    	System.out.printf("Total price is %.2f (inclusive of GST)\n", totalPrice);
    	System.out.println("Confirm to book? Press Y for yes or N for no.");
    	char choice = sc.next().charAt(0);
    	if(choice=='Y') {
    		System.out.println("Please enter your name: ");
    		//store name
    		System.out.println("Please enter your mobile number: ");
    		//store mobile number
    		System.out.println("Please enter your email address: ");
    		//store email
    		setTransactionId();
    		movieListing.updateSeats(choice, choice, choice);
    		System.out.println("Your booking is successful!"); 
    		System.out.println("Your transaction ID is : " + getTransactionId());
    	} else {
    		System.out.println("Your booking has been cancelled."); 
    	}
	}
	
	public void setTransactionId() {
        LocalDateTime dateTime = LocalDateTime.now() ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dateTimeStr = dateTime.format(formatter);
        this.transactionId = Integer.toString(movieListing.getCinemaHall()) + dateTimeStr;
    }
	
	public String getTransactionId() {
		return transactionId;
	}

}
