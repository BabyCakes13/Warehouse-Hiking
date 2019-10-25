package test.structures;

import org.junit.Test;
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

		for (int i = 0; i < 1000; i++) {
			int newRandom = (int) (Math.random() * range + Integer.MIN_VALUE);
			Station newStation = new Station(newRandom);
			Assert.assertEquals("Station " + newRandom, newStation.toString());
		}
	}

	@Test
	public void testHashCode() {
		int[] testStationNumbers = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };
		
		final int testRange = 2;
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
		Assert.assertNotEquals(1, firstStation);
		Assert.assertNotEquals(firstStation, null);
	}

}
