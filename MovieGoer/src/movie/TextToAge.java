package movie;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import movie.Movie.showingStatus.ageRating;


public class TextToAge extends AbstractBeanField{

	@Override
	public Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		ageRating ar = (ageRating) ageRating.valueOf(type, value);
		return ar;
	}
}

