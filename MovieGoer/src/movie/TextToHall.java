package movie;

import com.opencsv.bean.AbstractCsvConverter;

public class TextToHall extends AbstractCsvConverter {
	@Override
	public Object convertToRead(String value) {
		Hall h = new Hall();

	}
}
