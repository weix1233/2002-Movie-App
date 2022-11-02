package admin;

import java.io.FileNotFoundException;

public interface Login {
	public Boolean validate() throws IllegalStateException, FileNotFoundException;
}
