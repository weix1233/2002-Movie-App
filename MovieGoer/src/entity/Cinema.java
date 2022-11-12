package entity;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import control.TextToHall;
/**
 * Represents a single cinema location
 * @author SS4 Group 4
 *
 */
public class Cinema {
	/**
	 * Name of the cinema
	 */
	@CsvBindByName
	private String name;
	/**
	 * List of the halls in the cinema
	 */
	@CsvBindAndSplitByName(elementType = Hall.class, collectionType = ArrayList.class, splitOn = ",", converter = TextToHall.class)
	private List<Hall> halls;
	/**
	 * ID of the cinema
	 */
	@CsvBindByName
	private String cinemaID;
	/**
	 * Whether cinema is platinum
	 */
	@CsvBindByName
	private boolean isPlatinum;
	/**
	 * Creates a new Cinema with the following parameters
	 * @param halls List of the cinema halls
	 * @param name Name of the cinema
	 * @param cid ID of the cinema
	 * @param plat Whether cinema is platinum
	 */
	public Cinema(List<Hall> halls, String name, String cid, boolean plat) {
		this.name = name;
		this.halls = halls;
		this.cinemaID = cid;
		this.isPlatinum = plat;
	}
	/**
	 * Creates an empty Cinema object
	 */
	public Cinema() {
	}
	/**
	 * Gets the name of the cinema
	 * @return The name of the cinema
	 */
	public String getName() {
		return name;
	}
	/**
	 * Changes the name of the cinema
	 * @param name New name of the cinema
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets a list of hall IDs for the cinema
	 * @return list of hall IDs
	 */
	public List<Integer> getHallID() {
		List<Integer> hallID = new ArrayList<Integer>();
		for (int i = 0; i < halls.size(); i++) {
			hallID.add(halls.get(i).getHallID());
		}
		return hallID;
	}
	/**
	 * Gets a Hall given the ID of the hall
	 * @param hallID ID of the hall
	 * @return Hall object with the specified hall ID
	 */
	public Hall getHall(int hallID) {
		return halls.get(hallID);
	}
	/**
	 * Changes platinum status of the cinema
	 * @param plat New platinum status of the cinema
	 */
	public void setIP(boolean plat) {
		this.isPlatinum = plat;
	}
	/**
	 * Changes ID of the cinema
	 * @param cid New cinema ID
	 */
	public void setCinemaID(String cid) {
		this.cinemaID = cid;
	}
	/**
	 * Gets the ID of the cinema
	 * @return The cinema ID
	 */
	public String getCinemaID() {
		return this.cinemaID;
	}
	/**
	 * Gets the platinum status of the cinema
	 * @return Platinum status
	 */
	public boolean getIP() {
		return this.isPlatinum;
	}
}
