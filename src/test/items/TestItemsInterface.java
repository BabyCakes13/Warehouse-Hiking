package test.items;

public interface TestItemsInterface {
	final int TIMES = 100;
	final int[] DOMAIN_COVER_NUMBERS = { Integer.MIN_VALUE, 0, Integer.MAX_VALUE };

	void testHashCode();

	void testEquals();

	void testToString();
}
