package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import items.Basket;
import items.Packet;
import search.PossiblePaths;
import search.Searcher;
import structures.Station;

public class InputManager {
	public static final String INPUT_MESSAGE = "Enter the basket number. Enter x to stop.";
	public static final String WRONG_INPUT_MESSAGE = "Wrong type of input. Try again or press X to exit.";
	public static final String EXIT_TRIGGER = "X";

	Scanner scan;
	ObjectBuilder ob;

	public InputManager(ObjectBuilder ob) {
		this.scan = new Scanner(System.in);
		this.ob = ob;
	}

	public Scanner getScan() {
		return scan;
	}

	public void setScan(Scanner scan) {
		this.scan = scan;
	}

	public void askInput() {
		System.out.println(INPUT_MESSAGE);

		while (true) {
			String command = scan.nextLine();

			try {
				int basketNumber = Integer.parseInt(command);
				startProcessing(basketNumber);
			} catch (NumberFormatException e) {
				if (command.equals(EXIT_TRIGGER)) {
					System.out.println("X pressed.\nStopping.");
					break;
				}
				System.out.println("Wrong input value. Try again.");
				continue;
			}
		}
	}

	public void startProcessing(int basketNumber) {
		Basket basket = checkBasketExists(basketNumber);

		if (basket != null) {
			ArrayList<Packet> carriedPackets = getPacketsFromBasket(basket);
			for (Packet packetInBasket : carriedPackets) {
				Packet packet = checkPacketExists(packetInBasket.getPacketNumber());

				if (packet != null) {
					System.out.println("Started searching for paths for packet " + packet.getPacketNumber());
					
					Searcher search = new Searcher(ob);
					ArrayList<Station> requestedStations = checkPacketStationsRequest(packet);
					ArrayList<Station> allStations = requestedStations;
					allStations.add(0, new Station(0));
					allStations.add(allStations.size(), new Station(100));
					
					System.out.println(allStations.toString());
					
					PossiblePaths foundPaths = search.searchAllPaths(allStations);
					if (foundPaths.getSize() < 1) {
						System.out.println("No paths found.");
					} else {
						System.out.println(foundPaths.toString());
					}
				} else {
					System.out.println("No packet with number " + packetInBasket.getPacketNumber());
				}
			}

		}
	}

	public Basket checkBasketExists(int basket) {
		Map<Basket, ArrayList<Packet>> allPackets = ob.getBaskets();
		Basket searchedBasket = new Basket(basket);

		if (allPackets.containsKey(searchedBasket) == false) {
			System.out.println("Basket does not exist.");
			return null;
		}

		return searchedBasket;
	}

	public Packet checkPacketExists(int packet) {
		Map<Packet, ArrayList<Station>> allPackets = ob.getPackets();
		Packet searchedPacket = new Packet(packet);
		
		if (allPackets.containsKey(searchedPacket) == false) {
			System.out.println("Package does not exist.");
			return null;
		}

		return searchedPacket;
	}

	public ArrayList<Packet> getPacketsFromBasket(Basket basket) {
		ArrayList<Packet> carriedPackets = new ArrayList<>();
		Map<Basket, ArrayList<Packet>> allBaskets = ob.getBaskets();

		carriedPackets = allBaskets.get(basket);
		return carriedPackets;
	}
	
	public ArrayList<Station> checkPacketStationsRequest(Packet packet) {
		ArrayList<Station> stationsRequest = ob.getPackets().get(packet);
		return stationsRequest;
	}

	public void searchPathsForPackage(Packet packet) {
		ArrayList<Station> packetRequiredStations = ob.getPackets().get(packet);
		System.out.println(packetRequiredStations);
	}
}
