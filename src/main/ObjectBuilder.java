package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import files.FileManager;
import items.Basket;
import items.Graph;
import items.Packet;
import items.Station;

/**
 * Class which initialises the structures (baskets, packets and warehouse) with
 * the values read from the input files.
 * 
 * @author babycakes
 *
 */
public class ObjectBuilder {
	private Map<Packet, ArrayList<Station>> packets;
	private Map<Basket, ArrayList<Packet>> baskets;
	Graph graph;

	/**
	 * Constructor of the class ObjectBuilder, which creates a new instance of the
	 * structures. Each structure has some requirements as value for the key. The
	 * Packet class has some stations which are required to be passed by the moving
	 * packet. The Basket object has a carried packet. The Warehouse object is as a
	 * directed graph, represented by the edge list.
	 */
	public ObjectBuilder() {
		packets = new HashMap<>();
		baskets = new HashMap<>();
		graph = new Graph();
	}

	/**
	 * Method which populates the packet map with information read from the given
	 * file path.
	 * 
	 * @param filePath String: The full path to the file containing the packet
	 *                 information.
	 */
	public void buildPackets(String filePath) {
		FileManager fm = new FileManager(filePath);
		Map<Integer, ArrayList<Integer>> dataExtracted = fm.extractData();
		// fm.printDataExtracted();

		for (Map.Entry<Integer, ArrayList<Integer>> data : dataExtracted.entrySet()) {
			Packet packet = new Packet(data.getKey());
			ArrayList<Station> stationsRequest = Station.convertArrayToStation(data.getValue());

			packets.putIfAbsent(packet, stationsRequest);
		}
	}

	/**
	 * Method to get the packets.
	 * 
	 * @return Map<Packet, <ArrayList<Station>>
	 */
	public Map<Packet, ArrayList<Station>> getPackets() {
		return this.packets;
	}

	/**
	 * Method to build the basket structure, based on the data extracted from the
	 * basket requirements file.
	 * 
	 * @param filePath
	 */
	public void buildBaskets(String filePath) {
		FileManager fm = new FileManager(filePath);
		Map<Integer, ArrayList<Integer>> dataExtracted = fm.extractData();
		// fm.printDataExtracted();

		for (Map.Entry<Integer, ArrayList<Integer>> data : dataExtracted.entrySet()) {
			Basket basket = new Basket(data.getKey());
			ArrayList<Packet> containedPackets = Packet.convertArrayToPacket(data.getValue());

			baskets.putIfAbsent(basket, containedPackets);
		}
	}

	/**
	 * Method which returns the current available baskets read from the basket file
	 * and the packets they carry.
	 * 
	 * @return Map<Basket, ArrayList<Packet>>: The Map which contains a Basket as
	 *         the key and a list of packets it carries.
	 */
	public Map<Basket, ArrayList<Packet>> getBaskets() {
		return this.baskets;
	}

	/**
	 * Method which builds the workstation by adding its stations and paths from the
	 * graph file. For each file row, representing the start and end of a path, it
	 * adds that path to the warehouse.
	 * 
	 * @param filePath String: The full path to the packets, baskets and graph
	 *                 files, containing these objects.
	 */
	public void buildGraph(String filePath) {
		FileManager fm = new FileManager(filePath);
		Map<Integer, ArrayList<Integer>> dataExtracted = fm.extractData();

		for (Map.Entry<Integer, ArrayList<Integer>> data : dataExtracted.entrySet()) {
			int startStation = data.getKey();

			ArrayList<Integer> endStations = data.getValue();
			for (Integer endStation : endStations) {
				graph.addPath(startStation, endStation);
			}

		}
	}

	/**
	 * Method which returns the current graph containing the warehouse information.
	 * 
	 * @return Graph: The graph containing the stations and paths.
	 */
	public Graph getGraph() {
		return this.graph;
	}

	/**
	 * Method to display the packets and their station requirements.
	 */
	public void displayPackets() {
		System.out.println("\nDISPLAYING PACKETS.");
		this.packets.forEach((packet, stationsRequirement) -> System.out.println(packet + ": " + stationsRequirement));
	}

	/**
	 * Method to display the baskets and the packets they carry.
	 */
	public void displayBaskets() {
		System.out.println("\nDISPLAYING BASKETS.");
		this.baskets.forEach((basket, containedPacket) -> System.out.println(basket + ": " + containedPacket));
	}

	/**
	 * Method to display the warehouses's stations and paths.
	 */
	public void displayGraph() {
		Map<Station, ArrayList<Station>> stations = this.graph.getGraph();

		System.out.println("\nDISPLAYING STATIONS.");
		stations.forEach((packet, stationsRequirement) -> System.out.println(packet + ": " + stationsRequirement));
	}
}
