package movie_info;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Booking{
	private Seat[][] seat = new Seat[9][13];
	private String transactionId;
	private double totalPrice = 0.0;;
	private ArrayList<Ticket> tickets = new ArrayList<>();
	private ArrayList<Integer> selectedRow = new ArrayList<Integer>();
	private ArrayList<Integer> selectedCol = new ArrayList<Integer>();
	
	public Booking() {
		for (int i=0; i<9; i++){
			for (int j=0; j<13; j++) {
				seat[i][j] = new Seat(i+1, j+1);
			}
        }
	}
	
	public void assignSeat(int row, int col, int cust_id) {
		if (!seat[row-1][col-1].isOccupied()){
            seat[row-1][col-1].assign(cust_id);
            System.out.println("Seat assigned!");
        }
        else
            System.out.println("Seat already assigned to a customer.");
	}
	
	public void unAssignSeat(int row, int col) {
		if (seat[row-1][col-1].isOccupied()){
            seat[row-1][col-1].unAssign();
            System.out.println("Seat Unassigned!");
        }
        else{
            System.out.println("Seat Empty.");
        }
	}
	
	public void displaySeats(){
		char base = 'A';
		Scanner sc = new Scanner(System.in);
		System.out.println("===================Screen================");
		for(int i=0; i<9; i++) {
			char rowLetter = (char)((int)base + i);
			System.out.printf("%c ", rowLetter);
			for(int j=0; j<13; j++) {
				if(j==6) { System.out.printf("   "); }
				else { System.out.printf("%s", seat[i-1][j-1].seatSlot()); }
			}
			System.out.printf("\n");
		}
		System.out.println("=================Entrance================");
		System.out.println("[ ] Seat Available, [X] Seat Taken");
		System.out.println("Please select your seat(s): ");
		System.out.println("Enter your seat one at a time in the format A1 = 11, B3 = 23. Enter 0 when done.");
		System.out.println("Enter row number: ");
		int row = sc.nextInt();
		while(row != 0) {
			System.out.println("Enter column number: ");
			int col = sc.nextInt();
			assignSeat(row, col, seat[row][col].getCustomerId());
			selectedRow.add(row);
			selectedCol.add(col);
			Ticket ticket = new Ticket(movieType, cinemaClass, dayType, seat[row][col]);
			tickets.add(ticket);
			totalPrice += ticket.getTicketPrice();
			System.out.println("Enter row number: ");
			row = sc.nextInt();
		}
	}
	
	public void displayBooking() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=========================================");
    	System.out.printf("Confirmation of Booking:");
    	System.out.printf("Movie: %s\n", getMovieTitle());
    	System.out.printf("Cinema: %s\n", getCinemaId()); 	
    	System.out.printf("Hall: %d\n", getHallNo());
    	System.out.printf("Showtime: %d\n", getShowTime());
    	System.out.printf("Total price is %.2f (inclusive of GST)\n", totalPrice);
    	System.out.println("Confirm to book? Press Y for yes or N for no.");
    	char choice = sc.next().charAt(0);
    	if(choice=='Y') {
    		setTransactionId();
    		System.out.println("Your booking is successful!");
    		System.out.println("Your transaction ID is : " + getTransactionId());
    	} else {
    		for (int i=0; i<selectedRow.size(); i++) {
    		      for (int j=0; j<selectedCol.size(); j++) {
    		    	  unAssignSeat(selectedRow.get(i), selectedCol.get(j));
    		      }
    		}
    		selectedRow.clear();
    		selectedCol.clear();
    	}
	}
	
	public void setTransactionId() {
        LocalDateTime dateTime = LocalDateTime.now() ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dateTimeStr = dateTime.format(formatter);
        this.transactionId = getCinemaId() + dateTimeStr;
    }
	
	public String getTransactionId() {
		return transactionId;
	}

}
