package items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import structures.Station;

public class Graph {
	private Map<Station, ArrayList<Station>> graph;

	public Graph() {
		System.out.println("Generated empty graph.");
		this.graph = new HashMap<Station, ArrayList<Station>>();
	}

	public Map<Station, ArrayList<Station>> getGraph() {
		return graph;
	}

	public ArrayList<Station> getConnectedStations(int stationNumber) {
		return this.graph.get(new Station(stationNumber));
	}

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

	public void addPath(int startStation, int endStation) {
		Station node1 = new Station(startStation);
		Station node2 = new Station(endStation);

		this.addStation(startStation);
		this.addStation(endStation);

		// System.out.println("Added edge between " + startStation + " and " +
		// endStation);
		this.graph.get(node1).add(node2);
	}
}
