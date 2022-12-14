package control;

import com.opencsv.bean.AbstractCsvConverter;

import entity.Hall;

/**
 * Utility class to convert text in CSV file to a Hall object
 * 
 * @author SS4 Group 4
 *
 */
public class TextToHall extends AbstractCsvConverter {
	/**
	 * Converts the text information in the CSV file into a Hall object
	 */
	@Override
	public Object convertToRead(String value) {
		Hall h = new Hall();
		String[] split = value.split("\\|", 3);
		// for(int i = 0;i < split.length;i++)
		// System.out.printf("TTH: %s\n", split[i]);
		h.setHallID(Integer.parseInt(split[0]));
		h.setIP(Boolean.parseBoolean(split[1]));
		h.setAvailableShowTimes(split[2]);
		return h;
	}

}