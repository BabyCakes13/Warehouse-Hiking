package structures;

import java.util.ArrayList;

/*
 * Class representing a node in a graph formed of stations.
 * @param stationNumber: The number of the station represented by the current node.
 */
public class Station {
	public static int stationID;
	private int stationNumber;
	
	public Station(int stationNumber) {
		this.stationNumber = stationNumber;
		stationID++;
	}
	
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}
	
	public int getStationNumber() {
		return this.stationNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stationNumber;
		return result;
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
	
	
	public static ArrayList<Station> convertArrayToStation(ArrayList<Integer> integerArray) {
		ArrayList<Station> stationArray = new ArrayList<>();
		integerArray.forEach(integer -> stationArray.add(new Station(integer)));
		
		return stationArray;
	}
}
