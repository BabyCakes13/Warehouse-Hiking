package structures;

/**
 * Class which holds a path/edge between two station numbers.
 * 
 * @author babycakes
 *
 */
public class Edge {
	@SuppressWarnings("unused")
	private int startStation;
	@SuppressWarnings("unused")
	private int endStation;

	/**
	 * Constructor of the Edge class, initialising the station numbers.
	 * 
	 * @param station1: The station number of the starting station point. Directed
	 *                  towards station 2.
	 * @param station2: The station number of the ending station point.
	 */
	public Edge(int station1, int station2) {
		this.startStation = station1;
		this.endStation = station2;
	}

}
