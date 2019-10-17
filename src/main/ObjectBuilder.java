package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import files.FileManager;
import items.Basket;
import items.Graph;
import items.Packet;
import structures.Station;

public class ObjectBuilder {
	private Map<Packet, ArrayList<Station>> packets;
	private Map<Basket, ArrayList<Packet>> baskets;
	Graph graph;

	public ObjectBuilder() {
		packets = new HashMap<>();
		baskets = new HashMap<>();
		graph = new Graph();
	}

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

	public Map<Packet, ArrayList<Station>> getPackets() {
		return this.packets;
	}

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

	public Map<Basket, ArrayList<Packet>> getBaskets() {
		return this.baskets;
	}

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

	public Graph getGraph() {
		return this.graph;
	}

	public void displayPackets() {
		System.out.println("\nDISPLAYING PACKETS.");
		this.packets.forEach((packet, stationsRequirement) -> System.out.println(packet + ": " + stationsRequirement));
	}

	public void displayBaskets() {
		System.out.println("\nDISPLAYING BASKETS.");
		this.baskets.forEach((basket, containedPacket) -> System.out.println(basket + ": " + containedPacket));
	}

	public void displayGraph() {
		Map<Station, ArrayList<Station>> stations = this.graph.getGraph();

		System.out.println("\nDISPLAYING STATIONS.");
		stations.forEach((packet, stationsRequirement) -> System.out.println(packet + ": " + stationsRequirement));
	}
}
