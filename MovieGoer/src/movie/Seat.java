package movie;

// Seat info - row number, column number and availability.

public class Seat{
	private final int row;
    private final int col;
    private boolean assigned;
    private String seatId;
    private int customerId;

    public Seat(int row, int col, String seatId) {
        this.row = row;
        this.col = col;
        this.seatId = seatId;
        assigned = false;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public String getSeatId() {
        return seatId;
    }
	
	public int getCustomerId() {
		return customerId;
	}
	
	public boolean isOccupied() {
		return assigned;
	}
	
	public void assign(int cust_id) {
		customerId = cust_id;
		assigned = true;
	}
	
	public void unAssign() {
		assigned = false;
	}
	
	public void setSeatId(int row, int col) {
        char base = 'A';
        char letterRow = (char)((int)base + row);
        this.seatId = letterRow + "" + col;
    }
    
    public String seatSlot() {
        if (!assigned) return "[ ]";
        else return "[X]";
    }
}
