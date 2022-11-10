package entity;

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
	private Cinema cinema;
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
	public Ticket(Cinema cinema, MovieListing movieListing, ticketType ticType, int row, int col) {
		this.cinema = cinema;
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
	 */
	public void calculateTicketPrice() {
		String[] dayTime = movieListing.getShowtime().split(" ");
		String day = dayTime[0];
		String time = dayTime[1];

		double basePrice = 5.0;

		// arbitrary price setting
		if (movieListing.getType() == screenType.TWO_D) {
			basePrice += 2.0;
		} else
			basePrice += 4.0;

		if (cinema.getIP() == false) {
			basePrice += 2.0;
		} else
			basePrice += 4.0;

		if (day.equals("FRI")) {
			if (Integer.parseInt(time) < 1800)
				basePrice += 1.0;
			else
				basePrice += 2.0;
		} else if (day.equals("SAT") || day.equals("SUN")) {
			basePrice += 2.0;
		} else if (day.equals("PH")) {
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
	 */
	public double getTicketPrice() {
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
