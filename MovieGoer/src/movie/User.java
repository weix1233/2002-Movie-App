package movie;

import java.util.*;

public class User {
	private String name;
	private int mobileNo;
	private String email;
	//bookingHistory is a string of movieTitle and transactionID
	private ArrayList<String> bookingHistory;
		
	public User(String name, String email, int mobileNo){	    	
		this.email= email;	    	
		this.mobileNo= mobileNo;	  
		this.name= name;
		this.bookingHistory = new ArrayList<String>();
	}
		
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
	
}
