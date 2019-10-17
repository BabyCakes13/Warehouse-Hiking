package items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import structures.Station;

/**
 * Class which represents the paths and stations from the warehouse, acting as a
 * directed graph. The warehouse has a number of stations, connected to each
 * other by directed paths.
 * 
 * @author babycakes
 *
 */
public class Graph {
	private Map<Station, ArrayList<Station>> graph;

	/**
	 * Initialise a new Map which will have each station as key and its conenctions
	 * as value.
	 */
	public Graph() {
		this.graph = new HashMap<Station, ArrayList<Station>>();
	}

	/**
	 * Method to get the warehouse.
	 * 
	 * @return Map<Station, ArrayList<Station>>: The graph used to represent the
	 *         warehouse.
	 */
	public Map<Station, ArrayList<Station>> getGraph() {
		return this.graph;
	}

	/**
	 * Method to return connected stations for a station.
	 * 
	 * @param stationNumber int: The number of the station for which we search the
	 *                      neighbours.
	 * @return ArrayList<Station>: An array fromed of all Stations which are in
	 *         direct touch with the input one.
	 */
	public ArrayList<Station> getConnectedStations(int stationNumber) {
		return this.graph.get(new Station(stationNumber));
	}

	/**
	 * Method to add a station to the warehouse (graph).
	 * 
	 * @param stationNumber int: The number of the new station.
	 * @return True: if the Station does not already exist and could be added, false
	 *         otherwise.
	 */
	public boolean addStation(int stationNumber) {
		ArrayList<Station> result = (ArrayList<Station>) graph.putIfAbsent(new Station(stationNumber),
				new ArrayList<>());

		if (result == null) {
			// System.out.println("Added station with number " + stationNumber);
			return true;
		}
		// System.out.println("Couldn't add station with number " + stationNumber);
		return false;
	}

	/**
	 * Method to remove a station from the warehouse.
	 * 
	 * @param stationNumber int: The number of the station to be deleted.
	 * @return True: if the Station exists in the warehouse and could be safely
	 *         deleted, False otherwise.
	 */
	public boolean removeStation(int stationNumber) {
		boolean result = this.graph.entrySet().removeIf(entry -> entry.getKey().getStationNumber() == stationNumber);

		if (result == true) {
			// System.out.println("Succesfully removed station with number " +
			// stationNumber);
			return true;
		}

		// System.out.println("Could not remove station with number " + stationNumber);
		return false;
	}

	/**
	 * Method to add a path between two Stations, by adding the stations which do
	 * not exist and adding to the start station's reachable neighbours the
	 * destination station. The warehouse is represented by a directed graph, hence
	 * we need start and destination.
	 * 
	 * @param startStation     int: The number of the start station to be added.
	 * @param endStationNumber int: The number of the destination station to be
	 *                         added.
	 */
	public void addPath(int startStationNumber, int endStationNumber) {
		Station node1 = new Station(startStationNumber);
		Station node2 = new Station(endStationNumber);

		this.addStation(startStationNumber);
		this.addStation(endStationNumber);

		// System.out.println("Added edge between " + startStation + " and " +
		// endStation);
		this.graph.get(node1).add(node2);
	}
}
