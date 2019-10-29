package test.items;

import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import items.Packet;

public class TestPacket implements TestItemsInterface {

	private final int times = 1000;
	private final int[] domainCoverNumbers = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };

	/**
	 * Method to test the {@link Packet#hashCode()} method. Tests by comparing the
	 * hash codes of numbers varying around the edges and middle of the domain.
	 * ({@link #times} numbers before and after the edges and middle.
	 * 
	 * @author babycakes
	 */
	@Test
	@Override
	public void testHashCode() {
		for (int number : domainCoverNumbers) {
			for (int i = -times; i <= times; i++) {
				Packet firstPacket = new Packet(number + i);
				Packet secondPacket = new Packet(number + i);

				Assert.assertEquals(firstPacket.hashCode(), secondPacket.hashCode());
			}
		}
	}

	/**
	 * Method which tests the {@link Packet#equals(Object)} method.
	 * 
	 * @author babycakes
	 */
	@Test
	@Override
	public void testEquals() {
		Packet firstPacket = new Packet(1);
		Packet secondPacket = new Packet(1);
		Packet thirdPacket = new Packet(2);

		Assert.assertEquals(firstPacket, firstPacket);
		Assert.assertEquals(firstPacket, secondPacket);
		Assert.assertNotEquals(firstPacket, thirdPacket);
		Assert.assertNotEquals(firstPacket, null);
		Assert.assertNotEquals(firstPacket, 42);
	}

	/**
	 * Method which tests the {@link Packet#toString()} method. Tests by comparing
	 * the expected String form of the {@link Packet} class with the generated one
	 * for a random packet number.
	 * 
	 * @author babycakes
	 */
	@Test
	@Override
	public void testToString() {
		double domainRange = (double) Integer.MAX_VALUE - Integer.MIN_VALUE + 1;
		int randomNumber = (int) (Math.random() * domainRange + Integer.MIN_VALUE);

		Packet newPacket = new Packet(randomNumber);
		Assert.assertEquals("Packet: " + randomNumber, newPacket.toString());
	}

	/**
	 * Method to test the {@link Packet#convertArrayToPacket(ArrayList) method.
	 */
	@Test
	public void testConvertArrayToPacket() {
		ArrayList<Packet> testPackets = new ArrayList<Packet>();
		ArrayList<Integer> testIntegers = new ArrayList<Integer>();

		for (int number : domainCoverNumbers) {
			for (int i = -times; i <= times; i++) {
				Packet newPacket = new Packet(number + i);

				testIntegers.add(number + i);
				testPackets.add(newPacket);
			}
		}

		Assert.assertEquals(testPackets, Packet.convertArrayToPacket(testIntegers));
	}

	/**
	 * Method to test the {@link Packet#getPacketNumber()} method. Tests by
	 * comparing the packet number given to the constructor and the number got back
	 * from the {@link Packet#getPacketNumber()} method. The test is done on numbers
	 * from the edges and middle of the domain.
	 * 
	 * @author babycakes
	 */
	@Test
	public void testGetPacketNumber() {
		for (int number : domainCoverNumbers) {
			for (int i = -times; i <= times; i++) {
				Packet newPacket = new Packet(number + i);

				Assert.assertEquals(number + i, newPacket.getPacketNumber());
			}
		}
	}
}
