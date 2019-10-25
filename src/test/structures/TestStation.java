package test.structures;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import structures.Station;

public class TestStation {

	@Test
	public void testToStringBasic() {
		int[] testStationNumbers = { -1, 0, 1 };

		for (int i : testStationNumbers) {
			Station newStation = new Station(i);
			Assert.assertEquals("Station " + i, newStation.toString());
		}
	}

	@Test
	public void testToStringGenerated() {
		double range = (double) Integer.MAX_VALUE - Integer.MIN_VALUE + 1;

		final int stationsNumber = 1000;
		for (int i = 0; i < stationsNumber; i++) {
			int newRandom = (int) (Math.random() * range + Integer.MIN_VALUE);
			Station newStation = new Station(newRandom);
			Assert.assertEquals("Station " + newRandom, newStation.toString());
		}
	}

	@Test
	public void testHashCode() {
		int[] testStationNumbers = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };
		
		final int testRange = 10;
		for(int stationNumber : testStationNumbers) {
			for (int i = -testRange ; i <= testRange ; i++) {
				Station firstStation = new Station(stationNumber + i);
				Station secondStation = new Station(stationNumber + i);
				Assert.assertEquals(firstStation.hashCode(), secondStation.hashCode());
			}
		}
	}
	
	@Test
	public void testEqualsEqual() {
		Station firstStation = new Station(1);
		Station secondStation = new Station(1);
		
		Assert.assertEquals(firstStation, firstStation);
		Assert.assertEquals(firstStation, secondStation);
		
		Assert.assertNotEquals(firstStation, 42);
		
		Assert.assertNotEquals(firstStation, null);
		
		Station thirdStation = new Station(2);
		Assert.assertNotEquals(thirdStation, firstStation);

	}
	
	@Test
	public void testConvertArrayToStation() {
		ArrayList<Station> testStations = new ArrayList<Station>();
		ArrayList<Integer> testIntegers = new ArrayList<Integer>();
		
		int[] testStationNumbers = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };
		final int testRange = 10;
		for(int stationNumber: testStationNumbers) {
			for(int i = -testRange; i <= testRange; i++) {
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
