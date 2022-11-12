package entity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
/**
 * Represents a user's details in the system
 * @author SS4 Group 4
 *
 */
public class User {
	/**
	 * First and last name of the user
	 */
	@CsvBindByName
	private String name;
	/**
	 * Phone number of the user
	 */
	@CsvBindByName
	private int mobileNo;
	/**
	 * Email address of the user
	 */
	@CsvBindByName
	private String email;
	//bookingHistory is a string of movieTitle and transactionID
	/**
	 * A list of bookings that the user has made
	 * Each entry is a string of movieTitle and transactionID
	 */
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private ArrayList<String> bookingHistory;
	/**
	 * Creates a new User with the following parameters and initialises bookingHistory to be empty
	 * @param name First and last name of the user
	 * @param email Email address of the user
	 * @param mobileNo Phone number of the user
	 */
	public User(String name, String email, int mobileNo){	    	
		this.email= email;	    	
		this.mobileNo= mobileNo;	  
		this.name= name;
		this.bookingHistory = new ArrayList<String>();
	}
	/**
	 * Creates a new empty User
	 */
	public User() {}
	/**
	 * Gets the phone number of the user
	 * @return The user's phone number
	 */
	public int getMobileNo() {
		return mobileNo;
	}
	/**
	 * Gets the email address of the user
	 * @return The user's email address
	 */
	public String getEmail() {
		return email;	
	}
	/**
	 * Gets the name of the user
	 * @return The user's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Changes the name of the user
	 * @param name The new name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Changes the phone number of the user
	 * @param mobileNo The new phone number of the user
	 */
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * Changes the email address of the user
	 * @param email The new email address of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Gets the booking history of the user
	 * @return A list of past bookings the user has made
	 */
	public ArrayList<String> getbookingHistory() {
        return bookingHistory;
    }
	/**
	 * Changes the booking history of the user
	 * @param bookingHistory A new list of bookings
	 */
    public void setbookingHistory(ArrayList<String> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }
    /**
     * Add a new booking to the booking history
     * @param newBooking A string of movieTitle and transactionID
     */
    public void addBookingHistory(String newBooking){
        if(bookingHistory.isEmpty()){
            this.bookingHistory = new ArrayList<>();
            this.bookingHistory.add(newBooking);
        }
        else{
            this.bookingHistory.add(newBooking);
        }
    }
    /**
     * Removes a booking from the booking history
     * @param removeBooking Booking to be removed
     */
    public void removeBooking(String removeBooking){
    	this.bookingHistory.remove(removeBooking);
    }
    /**
     * Writes a list of users to the user csv
     * @param u List of users to write 
     * @throws IOException
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     */
    public void writeBH(List<User> u) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    	Writer writer = new FileWriter("C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv");
    	StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
    	beanToCsv.write(u);
    	writer.close();
    }
}
