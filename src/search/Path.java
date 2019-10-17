package search;

import java.util.ArrayList;

import structures.Station;

public class Path {
	private ArrayList<Station> path;
	
	public Path() {
		this.path = new ArrayList<Station>();
	}

	public Path(Path pathObject) {
		this.path = new ArrayList<Station>(pathObject.path);
	}

	public void add(Station station) {
		path.add(station);
	}
	
	public void concatenatePaths(Path newPath) {
		this.path.addAll(newPath.path);
	}
	
	public void remove(Station station) {
		path.remove(station);
	}
	
	public void pop() {
		path.remove(path.size() - 1);
	}

	@Override
	public String toString() {
		return path.toString();
	}

}
