package entity;

import com.opencsv.bean.CsvBindByName;
/**
 * Represents the current configurations for the app that the admin can change
 * @author SS4 Group 4
 *
 */
public class Options {
	/**
	 * Allows admin to change the sort option. (1) Rating, (2) Sale, (3) Customer choice
	 */
	@CsvBindByName
	private int sortOption;
	/**
	 * Allows admin to change base price.
	 */
	@CsvBindByName
	private double basePrice;
	/**
	 * Empty constructor to allow CSVbind to work
	 */
	public Options() {}
	/**
	 * Changes the sort option
	 * @param o desired option
	 */
	public void setOption(int o) {
		this.sortOption = o;
	}
	/**
	 * Changes the base price
	 * @param p base price
	 */
	public void setBasePrice(double p) {
		this.basePrice = p;
	}
	/**
	 * Gets the sort option
	 * @return sort option
	 */
	public int getOption() {
		return this.sortOption;
	}
	/**
	 * Gets the base price
	 * @return base price
	 */
	public double getBasePrice() {
		return this.basePrice;
	}
}
