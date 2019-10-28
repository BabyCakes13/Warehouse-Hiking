package items;

/**
 * Class which holds the basket and its methods.
 * @author babycakes
 *
 */
public class Basket {
	private final int basketNumber;
	
	/**
	 * Constructor which initialises the basked ID read from the user input.
	 * @param basketID
	 */
	public Basket(int basketID) {
		this.basketNumber = basketID;
	}
	
	/**
	 * Override hashChode for object comparison.
	 */
	@Override
	public int hashCode() {
		return basketNumber;
	}

	/**
	 * Override equals for object comparison.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Basket other = (Basket) obj;
		if (basketNumber != other.basketNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Basket: " + basketNumber;
	}
}
