package movie;

public class Ticket{
	public enum movieType { TWO_D, THREE_D }
	public enum cinemaClass { STANDARD, PLATINUM }
	public enum dayType { WEEKDAY, WEEKEND, PH }
	public enum ticketType { ADULT, SENIOR, CHILD }
	private Seat seat;
	private double ticketPrice;
	
	private movieType movieType;
	private cinemaClass cinemaClass;
	private ticketType ticketType;
	private dayType dayType;
	
	public Ticket(movieType movieType, cinemaClass cinemaClass, dayType dayType, Seat seat) {
    	this.movieType = movieType;
    	this.cinemaClass = cinemaClass;
    	this.dayType = dayType;
    	this.seat = seat;
    }
	
	//Get methods
	public movieType getMovieType() {return movieType;}
	public cinemaClass getCinemaClass() {return cinemaClass;}
	public ticketType getTicketType() {return ticketType;}
	public dayType getdayType() {return dayType;}
	public Seat getSeat() {return seat;}
	public double getTicketPrice() {
		double basePrice = 0.0;
		
		//arbitrary price setting
		if(movieType == movieType.TWO_D) {
			basePrice += 2.0;
		} else basePrice += 4.0;
		
		if(cinemaClass == cinemaClass.STANDARD) {
			basePrice += 2.0;
		} else basePrice += 4.0;
		
		if(dayType == dayType.WEEKDAY) {
			basePrice += 3.0;
		} else if (dayType == dayType.WEEKEND) {
			basePrice += 6.0;
		} else basePrice += 9.0;
		
		if(ticketType == ticketType.SENIOR) {
			basePrice -= 2.0;
		} else if (ticketType == ticketType.CHILD) {
			basePrice += 1.0;
		} else basePrice += 2.0;
		
		//round to 2dp
		ticketPrice = Math.round(basePrice * 100.0) / 100.0;
		
		return ticketPrice;
	}
	
	//Set methods
	public void setMovieType(movieType movieType) {this.movieType = movieType;}
	public void setCinemaClass(cinemaClass cinemaClass) {this.cinemaClass = cinemaClass;}
	public void setTicketType(ticketType ticketType) {this.ticketType = ticketType;}
	public void setdayType(dayType dayType) {this.dayType = dayType;}
	public void setSeat(Seat seat) {this.seat = seat;}
	public void setTicketPrice(double ticketPrice) {this.ticketPrice = ticketPrice;}
	
}    
