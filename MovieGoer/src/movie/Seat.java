package movie;

// Seat info - row number, column number and availability.

public class Seat{
	private final int row;
    private final int col;
    private boolean assigned;
    private String seatID;
    private int customerID;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        assigned = false;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public String getSeatID() {
        return seatID;
    }
	
	public int getCustomerID() {
		return customerID;
	}
	
	public boolean isOccupied() {
		return assigned;
	}
	
	public void assign(int cust_ID) {
		customerID = cust_ID;
		assigned = true;
	}
	
	public void unAssign() {
		assigned = false;
	}
	
	public void setSeatID(int row, int col) {
        char base = 'A';
        char letterRow = (char)((int)base + row);
        this.seatID = letterRow + "" + col;
    }
    
    public String seatSlot() {
        if (!assigned) return "[ ]";
        else return "[X]";
    }
}
