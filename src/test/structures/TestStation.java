package test.structures;

import org.junit.Test;
import org.junit.Assert;

import structures.Station;

public class TestStation {
	
	@Test
	public void testToStringBasic() {
		int[] testStationNumbers = {-1, 0, 1};
		
		for(int i : testStationNumbers) {
			Station newStation = new Station(i);
			Assert.assertEquals( "Station " + i, newStation.toString());
		}
	}

	@Test
	public void testToStringGenerated() {
		double range = (double)Integer.MAX_VALUE - Integer.MIN_VALUE + 1;
		
		for(int i=0; i<1000; i++) {
			int newRandom = (int) (Math.random() * range + Integer.MIN_VALUE);
			Station newStation = new Station(newRandom);
			Assert.assertEquals("Station " + newRandom, newStation.toString());
		}
	}
	
	@Test 
	public void testHashCode() {
		Station stationOne = new Station(1);
		Station stationTwo = new Station(1);

		Assert.assertEquals(stationOne.hashCode(), stationTwo.hashCode());
	}
	
}
