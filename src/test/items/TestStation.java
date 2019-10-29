package test.items;

import org.junit.Test;

import items.Station;

import java.util.ArrayList;

import org.junit.Assert;

public class TestStation implements TestItemsInterface {

	@Test
	@Override
	public void testToString() {
		double range = (double) Integer.MAX_VALUE - Integer.MIN_VALUE + 1;

		final int stationsNumber = 1000;
		for (int i = 0; i < stationsNumber; i++) {
			int newRandom = (int) (Math.random() * range + Integer.MIN_VALUE);
			Station newStation = new Station(newRandom);
			Assert.assertEquals("Station " + newRandom, newStation.toString());
		}
	}

	/**
	 * Method to test the hashCode function from the Station class. It tests that
	 * two generated Stations have the same hash code if they have the same station
	 * number. The station numbers are chosen to be in the vicinity of minimum
	 * integer value, maximum integer value and 0, with +- testRange numbers.
	 * 
	 * @author babycakes
	 */
	@Test
	@Override
	public void testHashCode() {
		int[] testStationNumbers = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };

		final int testRange = 10;
		for (int stationNumber : testStationNumbers) {
			for (int i = -testRange; i <= testRange; i++) {
				Station firstStation = new Station(stationNumber + i);
				Station secondStation = new Station(stationNumber + i);
				Assert.assertEquals(firstStation.hashCode(), secondStation.hashCode());
			}
		}
	}

	@Test
	@Override
	public void testEquals() {
		Station firstStation = new Station(1);
		Station secondStation = new Station(1);
		Station thirdStation = new Station(2);

		Assert.assertEquals(firstStation, firstStation);
		Assert.assertEquals(firstStation, secondStation);
		Assert.assertNotEquals(firstStation, 42);
		Assert.assertNotEquals(firstStation, null);
		Assert.assertNotEquals(thirdStation, firstStation);

	}

	@Test
	public void testConvertArrayToStation() {
		ArrayList<Station> testStations = new ArrayList<Station>();
		ArrayList<Integer> testIntegers = new ArrayList<Integer>();

		int[] testStationNumbers = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };
		final int testRange = 10;
		for (int stationNumber : testStationNumbers) {
			for (int i = -testRange; i <= testRange; i++) {
				testStations.add(new Station(stationNumber + i));
				testIntegers.add(stationNumber + i);
			}
		}
		Assert.assertEquals(testStations, Station.convertArrayToStation(testIntegers));
	}

	@Test
	public void testGetStationNumber() {
		Station testStation = new Station(1);
		Assert.assertEquals(1, testStation.getStationNumber());
	}
}
