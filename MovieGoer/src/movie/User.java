package movie_info;

public class User {
	private String name;
	private String customerId;
	private String mobileNo;
	private String email;
		
	public User(String name, String email, String mobileNo){	    	
		this.email= email;	    	
		this.mobileNo= mobileNo;	  
		this.name= name;
	}
		
	public String getMobileNo() {
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
		
	public String getCustomerId() {
		return customerId;
	}
		
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
