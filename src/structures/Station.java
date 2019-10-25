package structures;

import java.util.ArrayList;

/**
 * Class representing a Station in the warehouse.
 * 
 * @author babycakes
 *
 */
public class Station {
	public static int stationID;
	private int stationNumber;

	/**
	 * Constructor of the Station class which initialised the object with its
	 * station number and increases the station ID (#TODO needs usage).
	 * 
	 * @param stationNumber: The number of the station in the warehouse.
	 */
	public Station(int stationNumber) {
		this.stationNumber = stationNumber;
		stationID++;
	}

	/**
	 * Method to get the station number of the object.
	 * 
	 * @return: int: Station's number.
	 */
	public int getStationNumber() {
		return this.stationNumber;
	}

	@Override
	public int hashCode() {
		return stationNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (stationNumber != other.stationNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Station " + stationNumber;
	}

	/**
	 * Method to convert an array of integers to an array of Stations.
	 * 
	 * @param integerArray: ArrayList<Integer>: An list of int values to be
	 *                      transformed to Stations.
	 * @return ArrayList<Station>: The converted int list to Stations list.
	 */
	public static ArrayList<Station> convertArrayToStation(ArrayList<Integer> integerArray) {
		ArrayList<Station> stationArray = new ArrayList<>();
		integerArray.forEach(integer -> stationArray.add(new Station(integer)));

		return stationArray;
	}
}
