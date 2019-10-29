package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import items.Graph;
import items.Station;
import main.ObjectBuilder;

/**
 * Class which handles the searching process for a packet's requirements. It
 * searches paths between each station of the packet's requirements, and then
 * concatenates each one with the next station requirement, until the final
 * paths (from the absolute beginning and destination) are found.
 * 
 * @author babycakes
 *
 */
public class Searcher {
	Graph graph;
	ArrayList<PossibleSolutionPaths> solutionPaths;

	/**
	 * Constructor of the Searcher class, which creates a list of solution paths
	 * between the absolute beginning and end for a packet.
	 * 
	 * @param ob ObjectBuilder: The object which built the graph based on the file
	 *           and contains its data. Needed to get the warehouse.
	 */
	public Searcher(ObjectBuilder ob) {
		solutionPaths = new ArrayList<PossibleSolutionPaths>();
		this.graph = ob.getGraph();
	}

	/**
	 * Recurently search each path between the current station and the end one,
	 * keeping track of the visited nodes. For each current station, acknowledge its
	 * connected neigbours and add them to the visiting list maked as False. After
	 * adding a station to a path, mark it as True, aka added and visited.
	 * 
	 * @param currentStation:  Station: The station which is currently being
	 *                         visited.
	 * @param endStation:      Station: The end station goal of the path. When the
	 *                         end station is reached, a path is found.
	 * @param visitedStations: Map<Station, Boolean>: Map which holds stations which
	 *                         were visited or acknowledged (are a neighbour of the
	 *                         already visited ones.)
	 * @param currentPath:     The path which is existend till now, while it did not
	 *                         reach the end station.
	 * @param pathsList:       The list of paths found between the start station and
	 *                         the end station.
	 */
	private void searchPathRecurent(Station currentStation, Station endStation, Map<Station, Boolean> visitedStations,
			Path currentPath, PossibleSolutionPaths pathsList) {
		currentPath.add(currentStation);
		visitedStations.put(currentStation, true);

		if (currentStation.equals(endStation)) {
			pathsList.add(new Path(currentPath));

			// System.out.println("End station " + currentStation.getStationNumber());
			// System.out.println("Current Path: " + currentPath.toString());
			// System.out.println("All Paths: " + pathsList.toString());

		} else {
			// System.out.println("Visiting " + currentStation.getStationNumber());
			ArrayList<Station> connectedStations = this.graph.getConnectedStations(currentStation.getStationNumber());

			for (Station station : connectedStations) {
				if (visitedStations.get(station) == null || visitedStations.get(station) == false) {
					this.searchPathRecurent(station, endStation, visitedStations, currentPath, pathsList);
				}
			}
		}

		currentPath.remove(currentStation);
		visitedStations.put(currentStation, false);

	}

	/**
	 * Method which starts searching for paths between the start station and the end
	 * one by calling the recurrent path finding function.
	 * 
	 * @param startStation: Station: The station which will be the start of the
	 *                      searched paths.
	 * @param endStation:   Station: The destination station for all the searched
	 *                      paths.
	 * @return PossibleSolutionPaths: An object holding each possible paths between
	 *         the stations.
	 */
	private PossibleSolutionPaths searchPaths(Station startStation, Station endStation) {
		Map<Station, Boolean> visitedStations = new HashMap<>();
		PossibleSolutionPaths pathsList = new PossibleSolutionPaths();
		Path currentPath = new Path();

		this.searchPathRecurent(startStation, endStation, visitedStations, currentPath, pathsList);

		return pathsList;
	}

	/**
	 * Method which takes the station requests of the packet and searched paths
	 * between each two intermediary points, with the aim to combine them into paths
	 * between the first (start station) to the last (end station).
	 * 
	 * @param intermediaryStations: ArrayList<Station>: A list of the packet's
	 *                              stations request.
	 * @return ArrayList<PossibleSolutionPaths>: All the intermediary paths held in
	 *         the object.
	 */
	public ArrayList<PossibleSolutionPaths> searchIntermediaryPaths(ArrayList<Station> intermediaryStations) {
		ArrayList<PossibleSolutionPaths> possilbePathsSequence = new ArrayList<PossibleSolutionPaths>();

		int i;
		for (i = 0; i < intermediaryStations.size() - 1; i++) {
			Station start = intermediaryStations.get(i);
			Station end = intermediaryStations.get(i + 1);

			possilbePathsSequence.add(searchPaths(start, end));
		}

		// System.out.println(possilbePathsSequence.toString());
		return possilbePathsSequence;
	}

	/**
	 * Method which takes the stations request of the packet, searches for
	 * intermediary paths based on the intermediary stations, then concatenates the
	 * results to find the absolute path between the start and destination stations.
	 * 
	 * @param stationsRequest: ArrayList<Station>: The list of requested stations
	 *                         for the current packet which will be used as
	 *                         intermediary points for intermediary searching.
	 * @return PossibleSolutionPaths: The requested paths between the start and
	 *         destination stations, reaching the requesting stations in between in
	 *         the right order.|
	 */
	public PossibleSolutionPaths searchAllPaths(ArrayList<Station> stationsRequest) {
		ArrayList<PossibleSolutionPaths> possiblePathsList = searchIntermediaryPaths(new ArrayList<>(stationsRequest));
		PossibleSolutionPaths concatenatedPossiblePaths;
		PossibleSolutionPaths previousPossiblePaths = new PossibleSolutionPaths();
		int currentPossiblePathsNumber;

		concatenatedPossiblePaths = new PossibleSolutionPaths(possiblePathsList.get(0));
		previousPossiblePaths = new PossibleSolutionPaths(concatenatedPossiblePaths);

		for (int i = 1; i < possiblePathsList.size(); i++) {
			PossibleSolutionPaths currentPossiblePaths = possiblePathsList.get(i);

			// System.out.println("Previous Blob: " + previousPossiblePaths);
			// System.out.println("Current Blob: " + currentPossiblePaths + "\n");

			currentPossiblePathsNumber = currentPossiblePaths.getSize();
			concatenatedPossiblePaths = new PossibleSolutionPaths();

			for (int j = 0; j < currentPossiblePathsNumber; j++) {
				for (Path currentPath : currentPossiblePaths.possibleSolutionPaths) {
					for (Path previousPath : previousPossiblePaths.possibleSolutionPaths) {

						previousPath.pop();
						Path newPath = new Path(previousPath);
						newPath.concatenatePaths(currentPath);
						concatenatedPossiblePaths.add(newPath);

						// System.out.println("New path is: " + newPath.toString());
						// System.out.println("Concatenated path: " + concatenatedPaths);
					}
					// System.out.println("Next path: " + path);

				}
			}

			previousPossiblePaths = new PossibleSolutionPaths(concatenatedPossiblePaths);
		}

		return concatenatedPossiblePaths;
	}

}
