package movie;

public class User {
	private String name;
	private int mobileNo;
	private String email;
		
	public User(String name, String email, int mobileNo){	    	
		this.email= email;	    	
		this.mobileNo= mobileNo;	  
		this.name= name;
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
	
}
