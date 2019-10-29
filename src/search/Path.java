package search;

import java.util.ArrayList;

import items.Station;

/**
 * Class which represents a Path between two stations (begin, destination),
 * which contains a number of stations and a number of edges which need to be
 * reached to satisty the begin and end of the path.
 * 
 * @author babycakes
 *
 */
public class Path {
	private ArrayList<Station> path;

	/**
	 * Constructor of the CurrentPath class which initialises the array which holds
	 * the stations visited while reaching the destination.
	 */
	public Path() {
		this.path = new ArrayList<Station>();
	}

	/**
	 * Copy constructor of the class. Using an already existing CurrentPath object
	 * to create another one.
	 * 
	 * @param pathObject
	 */
	public Path(Path pathObject) {
		this.path = new ArrayList<Station>(pathObject.path);
	}

	/**
	 * Method which adds a new station to the current path.
	 * 
	 * @param station Station: The new Station object which was visited in order to
	 *                reach the destination.
	 */
	public void add(Station station) {
		path.add(station);
	}

	/**
	 * Method to concatenate two current paths into one, by adding the new one's
	 * reached stations to the current one.
	 * 
	 * @param newPath CurrentPath: The CurrentPath object which will give its
	 *                reached stations up to the current object path.
	 */
	public void concatenatePaths(Path newPath) {
		this.path.addAll(newPath.path);
	}

	/**
	 * Method to remove a station from the CurrentPath, aka unvisit it.
	 * 
	 * @param station Station: Station object to be removed.
	 */
	public void remove(Station station) {
		path.remove(station);
	}

	/**
	 * Method to remove the last visited station from the current path.
	 */
	public void pop() {
		path.remove(path.size() - 1);
	}

	@Override
	public String toString() {
		return path.toString();
	}

}
