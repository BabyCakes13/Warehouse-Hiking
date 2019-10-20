package items;

import java.util.ArrayList;

/**
 * Class which holds the packet object. Each packet objject has a packet number.
 * 
 * @author babycakes
 *
 */
public class Packet {
	private final int packetNumber;

	/**
	 * Constructor of the Packet class, which initialises the packet number with the
	 * one read from the file.
	 * 
	 * @param packetNumber
	 */
	public Packet(int packetNumber) {
		this.packetNumber = packetNumber;
	}

	/**
	 * Method to get the number of the packet.
	 * 
	 * @return int: The packet number.
	 */
	public int getPacketNumber() {
		return this.packetNumber;
	}

	/**
	 * Override method for object comparison.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + packetNumber;
		return result;
	}

	/**
	 * Override method for object comparison.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Packet other = (Packet) obj;
		if (packetNumber != other.packetNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Packet: " + packetNumber;
	}

	/**
	 * Static method for converting an array of integers to an array of packets.
	 * 
	 * @param integerArray ArrayList<Integer>: An array of integer values
	 *                     representing packet numbers.
	 * @return ArrayList<Packet>: An array of packets derived from the integers.
	 */
	public static ArrayList<Packet> convertArrayToPacket(ArrayList<Integer> integerArray) {
		ArrayList<Packet> packetArray = new ArrayList<>();
		integerArray.forEach(integer -> packetArray.add(new Packet(integer)));

		return packetArray;
	}
}
