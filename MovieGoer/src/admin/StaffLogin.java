package admin;

import com.opencsv.bean.CsvBindByName;

public class StaffLogin {
	@CsvBindByName
	private String username;
	@CsvBindByName
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
