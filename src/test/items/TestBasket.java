package test.items;

import items.Basket;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class which tests the {@link Basket} class.
 * 
 * @author babycakes
 *
 */
public class TestBasket {

	private final int times = 1000;
	private final int[] domainCoverNumbers = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };

	/**
	 * Method which tests the {@link Basket#hashCode()} method. Tests by comparing
	 * the hash codes for two {@link Basket} objects with the same basket number for
	 * 3 * {@link #times}, covering the possible basket number domain for
	 * {@link Basket#basketNumber}.
	 * 
	 * @author babycakes
	 */
	@Test
	public void testHashCode() {
		for (int number : domainCoverNumbers) {
			for (int i = -times; i < times; i++) {
				Basket firstBasket = new Basket(number + i);
				Basket secondBasket = new Basket(number + i);

				Assert.assertEquals(firstBasket.hashCode(), secondBasket.hashCode());
			}
		}
	}

	/**
	 * Method which tests the {@link Basket#equals(Object)}equals method.
	 * 
	 * @author babycakes
	 */
	@Test
	public void testEquals() {
		Basket firstBasket = new Basket(1);
		Basket secondBasket = new Basket(1);
		Basket thirdBasket = new Basket(2);

		Assert.assertEquals(firstBasket, firstBasket);
		Assert.assertEquals(firstBasket, secondBasket);
		Assert.assertNotEquals(firstBasket, thirdBasket);
		Assert.assertNotEquals(firstBasket, 42);
		Assert.assertNotEquals(firstBasket, null);
	}

	/**
	 * Method which tests the {@link Basket#toString()} method. Tests by comparing
	 * the generated Strings for {@link #times} and the actual expected String form.
	 * The numbers were chosen to cover the edges and middle of the domain.
	 * 
	 * @author babycakes
	 */
	@Test
	public void testToString() {
		double numberRange = (double) Integer.MAX_VALUE - Integer.MIN_VALUE + 1;

		for (int i = 0; i < times; i++) {
			int newRandom = (int) (Math.random() * numberRange + Integer.MIN_VALUE);
			Basket newBasket = new Basket(newRandom);

			Assert.assertEquals("Basket: " + newRandom, newBasket.toString());
		}
	}
}
