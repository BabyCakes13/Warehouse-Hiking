package structures;

/*
 * Class which holds a directed edge from two stations, 1 and 2.
 */
public class Edge {
	private int startStation;
	private int endStation;

	public Edge(int station1, int station2) {
		this.startStation = station1;
		this.endStation = station2;
	}
	
	public int getStartStation() {
		return startStation;
	}

	public void setStartStation(int station1) {
		this.startStation = station1;
	}

	public int getEndStation() {
		return endStation;
	}

	public void setEndStation(int station2) {
		this.endStation = station2;
	}

}
