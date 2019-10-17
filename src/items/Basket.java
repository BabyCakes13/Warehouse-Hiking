package items;

public class Basket {
	private final int basketNumber;
	
	public Basket(int basketID) {
		this.basketNumber = basketID;
	}

	public int getBasketNumber() {
		return basketNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basketNumber;
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
		Basket other = (Basket) obj;
		if (basketNumber != other.basketNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Basket [basketNumber=" + basketNumber + "]";
	}
}
