package search;

import java.util.ArrayList;

/**
 * Method which holds the possible CurrentPaths which could be solutions to
 * reach the end of the packet requests from the beginning station of the
 * warehouse to the end one.
 * 
 * @author babycakes
 *
 */
public class PossibleSolutionPaths {
	ArrayList<Path> possibleSolutionPaths;

	/**
	 * Constructor of the PossibleSolutionPaths which creates a new list which will
	 * hold paths found from one point to another.
	 */
	public PossibleSolutionPaths() {
		this.possibleSolutionPaths = new ArrayList<Path>();
	}

	/**
	 * Copy constructor of the class, which takes another PossibleSolutionPaths
	 * object the paths, and cretes a new one based on them.
	 * 
	 * @param ppObject: PossibleSolutionPaths: The object which contains possible
	 *                  paths between two points.
	 */
	public PossibleSolutionPaths(PossibleSolutionPaths ppObject) {
		this.possibleSolutionPaths = new ArrayList<Path>(ppObject.possibleSolutionPaths);
	}

	/**
	 * Add a new path to the possible ones.
	 * 
	 * @param path Path: The new possible path found.
	 */
	public void add(Path path) {
		this.possibleSolutionPaths.add(path);
	}

	/**
	 * Method to get how many possible paths were found.
	 * 
	 * @return int: Number of possible paths found.
	 */
	public int getSize() {
		return this.possibleSolutionPaths.size();
	}

	@Override
	public String toString() {
		String toString = "";

		for (Path path : possibleSolutionPaths) {
			toString = toString.concat("\n" + path);
		}

		return toString;
	}

}
