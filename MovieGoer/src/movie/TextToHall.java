package movie;

import com.opencsv.bean.AbstractCsvConverter;

public class TextToHall extends AbstractCsvConverter {
	@Override
	public Object convertToRead(String value) {
		Hall h = new Hall();
		String[] split = value.split("\\|",3);
		for(int i = 0;i < split.length;i++)
		//System.out.printf("TTH: %s\n", split[i]);
		h.setHallID(Integer.parseInt(split[0]));
		h.setAvailableShowTimes(split[1]);
		h.setShowTimes(split[2]);
		return h;
	}
	
}