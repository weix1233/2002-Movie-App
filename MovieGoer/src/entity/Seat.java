package entity;

// Seat info - row number, column number and availability.
/**
 * Represents a seat in a cinema hall
 * @author SS4 Group 4
 *
 */
public class Seat{
	/**
	 * Row of the seat
	 */
	private final int row;
	/**
	 * Column of the seat
	 */
    private final int col;
    /**
     * Whether the seat has already been assigned to a customer
     */
    private boolean assigned;
    /**
     * ID of the seat
     */
    private String seatID;
    /**
     * Creates a new seat that is unoccupied
     * @param row Row of the seat
     * @param col Column of the seat
     */
    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        assigned = false;
    }
    /**
     * Gets the column of the seat
     * @return Column of the seat
     */
    public int getCol() {
        return col;
    }
    /**
     * Gets the row of the seat
     * @return Row of the seat
     */
    public int getRow() {
        return row;
    }
    /**
     * Gets the ID of the seat
     * @return The seat's ID
     */
    public String getSeatID() {
        return seatID;
    }
	/**
	 * Gets the availability of the seat
	 * @return Whether the seat is occupied or not
	 */
	public boolean isOccupied() {
		return assigned;
	}
	/**
	 * Assign the seat to a customer
	 */
	public void assign() {
		assigned = true;
	}
	/**
	 * Unassign the seat
	 */
	public void unAssign() {
		assigned = false;
	}
	/**
	 * Changes the seat ID
	 * @param row Row of the seat
	 * @param col Column of the seat
	 */
	public void setSeatID(int row, int col) {
        char base = 'A';
        char letterRow = (char)((int)base + row);
        this.seatID = letterRow + "" + col;
    }
    /**
     * Visually represents the availability of the seat
     * @return [ ] if unoccupied, [X] if occupied
     */
    public String seatSlot() {
        if (!assigned) return "[ ]";
        else return "[X]";
    }
}
