package movie;

import movie.MovieListing.dayOfWeek;
import movie.MovieListing.screenType;

public class Ticket {
	protected enum ticketType {
		ADULT, SENIOR, CHILD
	}

	private double ticketPrice;
	private ticketType ticType;
	private MovieListing movieListing;
	private Cinema cinema;
	private int row;
	private int col;

	public Ticket(Cinema cinema, MovieListing movieListing, ticketType ticType, int row, int col) {
		this.cinema = cinema;
		this.movieListing = movieListing;
		this.ticType = ticType;
		this.row = row;
		this.col = col;
	}

	// Get methods
	public ticketType getTicType() {
		return ticType;
	}

	public double getTicketPrice() {
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

		return ticketPrice;
	}

	// Set methods
	public void setTicketTypes(ticketType ticType) {
		this.ticType = ticType;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

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
