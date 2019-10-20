package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import items.Basket;
import items.Packet;
import search.PossibleSolutionPaths;
import search.Searcher;
import structures.Station;

/**
 * Class which handles user input for reading the basket requirements and
 * trigerring the computation of finding the paths.
 * 
 * @author babycakes
 *
 */
public class InputManager {
	public static final String INPUT_MESSAGE = "Enter the basket number. Enter x to stop.";
	public static final String WRONG_INPUT_MESSAGE = "Wrong type of input. Try again or press X to exit.";
	public static final String EXIT_TRIGGER = "X";

	Scanner scan;
	ObjectBuilder ob;

	/**
	 * Constructor of the InputManager class, initialising a new scanner and an
	 * object builder.
	 * 
	 * @param ob ObjectBuilder: The builder which populates the items (baskets,
	 *           packets and warehouse) from the files.
	 */
	public InputManager(ObjectBuilder ob) {
		this.scan = new Scanner(System.in);
		this.ob = ob;
	}

	/**
	 * Method which asks the user for a basket and triggers the searching for paths
	 * for the packet in the basket.
	 */
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

	/**
	 * For each packet in the basket, find out its paths by adding the start and
	 * stop station of the warehouse and search for all paths starting from the
	 * start and ending with the end. If no paths found, displays that message.
	 * 
	 * @param basketNumber: The number of the basket for processing.
	 */
	private void startProcessing(int basketNumber) {
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

					// add the start and end of the warehouse stations
					allStations.add(0, new Station(0));
					allStations.add(allStations.size(), new Station(100));

					// start searching
					PossibleSolutionPaths foundPaths = search.searchAllPaths(allStations);
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

	/**
	 * Method to check that a basket with the input parameter exists in the basket
	 * map.
	 * 
	 * @param basket int: The basket number the user wants to process.
	 * @return Basket: The found Basket object, null otherwise.
	 */
	private Basket checkBasketExists(int basket) {
		Map<Basket, ArrayList<Packet>> allPackets = ob.getBaskets();
		Basket searchedBasket = new Basket(basket);

		if (allPackets.containsKey(searchedBasket) == false) {
			System.out.println("Basket does not exist.");
			return null;
		}

		return searchedBasket;
	}

	/**
	 * Method to check if the packet number exists in the packets map.
	 * 
	 * @param packet int: An integer representing the packet number of the requested
	 *               packet.
	 * @return Packet: The Packet object if found; null otherwise.
	 */
	private Packet checkPacketExists(int packet) {
		Map<Packet, ArrayList<Station>> allPackets = ob.getPackets();
		Packet searchedPacket = new Packet(packet);

		if (allPackets.containsKey(searchedPacket) == false) {
			System.out.println("Package does not exist.");
			return null;
		}

		return searchedPacket;
	}

	/**
	 * Get a list of all the packets which are carried by the input Basket.
	 * 
	 * @param basket Basket: The Basket object on which the searching is done.
	 * @return ArrayList<Packet>: A list with all the packets carried by the input
	 *         Basket, which will be used for searching of their paths.
	 */
	private ArrayList<Packet> getPacketsFromBasket(Basket basket) {
		ArrayList<Packet> carriedPackets = new ArrayList<>();
		Map<Basket, ArrayList<Packet>> allBaskets = ob.getBaskets();

		carriedPackets = allBaskets.get(basket);
		return carriedPackets;
	}

	/**
	 * Get the list of required stations for an input Packet.
	 * 
	 * @param packet Packet: A Packet object for which the searching is done.
	 * @return ArrayList<Station>: The list of all station requirements for the
	 *         packet.
	 */
	private ArrayList<Station> checkPacketStationsRequest(Packet packet) {
		ArrayList<Station> packetStationsRequest = ob.getPackets().get(packet);
		return packetStationsRequest;
	}
	
}
