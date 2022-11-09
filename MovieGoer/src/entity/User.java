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

public class User {
	@CsvBindByName
	private String name;
	@CsvBindByName
	private int mobileNo;
	@CsvBindByName
	private String email;
	//bookingHistory is a string of movieTitle and transactionID
	@CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = ";")
	private ArrayList<String> bookingHistory;
		
	public User(String name, String email, int mobileNo){	    	
		this.email= email;	    	
		this.mobileNo= mobileNo;	  
		this.name= name;
		this.bookingHistory = new ArrayList<String>();
	}
	public User() {}
	public int getMobileNo() {
		return mobileNo;
	}
		
	public String getEmail() {
		return email;	
	}
	   
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ArrayList<String> getbookingHistory() {
        return bookingHistory;
    }

    public void setbookingHistory(ArrayList<String> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }
    
    public void addBookingHistory(String newBooking){
        if(bookingHistory.isEmpty()){
            this.bookingHistory = new ArrayList<>();
            this.bookingHistory.add(newBooking);
        }
        else{
            this.bookingHistory.add(newBooking);
        }
    }

    public void removeBooking(String removeBooking){
        for(int i = 0; i < this.bookingHistory.size(); i++){
            if(this.bookingHistory.get(i) == removeBooking){
                this.bookingHistory.remove(i);
                return;
            }
        }
    }
    public void writeBH(List<User> u) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    	Writer writer = new FileWriter("C:\\Users\\user\\git\\2002-Movie-App\\MovieGoer\\database\\user\\user.csv");
    	StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
    	beanToCsv.write(u);
    	writer.close();
    }
}
