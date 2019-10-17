package items;

import java.util.ArrayList;

public class Packet {
	private final int packetNumber;

	public Packet(int packetNumber) {
		this.packetNumber = packetNumber;
	}

	public int getPacketNumber() {
		return packetNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + packetNumber;
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
		Packet other = (Packet) obj;
		if (packetNumber != other.packetNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Packet [packetNumber=" + packetNumber + "]";
	}
	
	public static ArrayList<Packet> convertArrayToPacket(ArrayList<Integer> integerArray) {
		ArrayList<Packet> packetArray = new ArrayList<>();
		integerArray.forEach(integer -> packetArray.add(new Packet(integer)));
		
		return packetArray;
	}
}
