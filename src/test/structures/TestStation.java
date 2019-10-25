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
		int[] testStationNumbers = { Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2,
									-1, 0, 1,
									Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE };
		
		for(int stationNumber : testStationNumbers) { 
			Station firstStation = new Station(stationNumber);
			Station secondStation = new Station(stationNumber);
			Assert.assertEquals(firstStation.hashCode(), secondStation.hashCode());
		}
	}

}
