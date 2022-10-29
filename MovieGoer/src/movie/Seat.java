package movie_info;

// Seat info - row number, column number, show-time and availability.

public class Seat{
	private final int row;
    private final int col;
    private boolean assigned;
    private int customerId;

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
    
    public String seatSlot() {
        if (!assigned) return "[ ]";
        else return "[X]";
    }
}
