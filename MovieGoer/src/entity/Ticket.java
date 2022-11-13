package entity;

import java.io.FileNotFoundException;

import control.ReadCSVFiles;
import entity.MovieListing.dayOfWeek;
import entity.MovieListing.screenType;
/**
 * Represents a ticket to be issued to the customer
 * @author SS4 Group 4
 *
 */
public class Ticket {
	/**
	 * Type of ticket (Adult, Senior or Child)
	 * @author SS4 Group 4
	 *
	 */
	public enum ticketType {
		ADULT, SENIOR, CHILD
	}
	/**
	 * Price of the ticket
	 */
	private double ticketPrice=0;
	/**
	 * Type of the ticket
	 */
	private ticketType ticType;
	/**
	 * Movie listing that the ticket is for
	 */
	private MovieListing movieListing;
	/**
	 * Cinema where the movie will be screening
	 */
	private Hall hall;
	/**
	 * Row of the seat
	 */
	private int row;
	/**
	 * Column of the seat
	 */
	private int col;
	/**
	 * Creates a new Ticket with the following parameters
	 * @param cinema Cinema where the movie is screening
	 * @param movieListing Movie Listing that the ticket is for
	 * @param ticType Type of ticket
	 * @param row Row of seat
	 * @param col Column of seat
	 */
	public Ticket(Hall hall, MovieListing movieListing, ticketType ticType, int row, int col) {
		this.hall = hall;
		this.movieListing = movieListing;
		this.ticType = ticType;
		this.row = row;
		this.col = col;
	}
	/**
	 * Gets the type of ticket
	 * @return The ticket type
	 */
	public ticketType getTicType() {
		return ticType;
	}
	/**
	 * Calculate the price of the ticket
	 * @throws FileNotFoundException 
	 * @throws IllegalStateException 
	 */
	public void calculateTicketPrice() throws IllegalStateException, FileNotFoundException {
		dayOfWeek day = movieListing.getDay();
		String time = movieListing.getShowtime();

		double basePrice = ReadCSVFiles.getOptions().get(0).getBasePrice();

		// arbitrary price setting
		if (movieListing.getType() == screenType.TWO_D) {
			basePrice += 2.0;
		} else
			basePrice += 4.0;

		if (hall.getIP() == false) {
			basePrice += 2.0;
		} else
			basePrice += 4.0;

		if (day == dayOfWeek.FRI) {
			if (Integer.parseInt(time) < 1800)
				basePrice += 1.0;
			else
				basePrice += 2.0;
		} else if (day == dayOfWeek.SAT || day == dayOfWeek.SUN) {
			basePrice += 2.0;
		} else if (day == dayOfWeek.PH) {
			basePrice += 4.0;
		} else {
			if (ticType == ticketType.SENIOR) {
				basePrice -= 4.0;
			} else if (ticType == ticketType.CHILD) {
				basePrice -= 2.0;
			} else
				basePrice += 0.0;
		}

		// add GST
		basePrice = 1.07 * basePrice;
		// round to 2dp
		ticketPrice = Math.round(basePrice * 100.0) / 100.0;
	}
	/**
	 * Gets the price of the ticket
	 * @return The ticket price
	 * @throws FileNotFoundException 
	 * @throws IllegalStateException 
	 */
	public double getTicketPrice() throws IllegalStateException, FileNotFoundException {
		if(ticketPrice==0) {
			calculateTicketPrice();
		}
		return ticketPrice;
	}

	/**
	 * Changes the type of the ticket
	 * @param ticType The new ticket type
	 */
	public void setTicketTypes(ticketType ticType) {
		this.ticType = ticType;
	}
	/**
	 * Changes the price of the ticket
	 * @param ticketPrice The new ticket price
	 */
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
}
