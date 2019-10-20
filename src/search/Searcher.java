package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import items.Graph;
import main.ObjectBuilder;
import structures.Station;

public class Searcher {
	Graph graph;
	ArrayList<PossibleSolutionPaths> concatenatedPaths;
	
	public Searcher(ObjectBuilder ob) {
		concatenatedPaths = new ArrayList<PossibleSolutionPaths>();
		this.graph = ob.getGraph();
	}
	
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
	
	private PossibleSolutionPaths searchPaths(Station startStation, Station endStation) {
		Map<Station, Boolean> visitedStations = new HashMap<>();
		PossibleSolutionPaths pathsList = new PossibleSolutionPaths();
		Path currentPath = new Path();

		this.searchPathRecurent(startStation, endStation, visitedStations, currentPath, pathsList);

		// System.out.println("All paths up 1: " + pathsList.toString());

		return pathsList;
	}
	
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
	
	public PossibleSolutionPaths searchAllPaths(ArrayList<Station> stationsRequest) {
		ArrayList<PossibleSolutionPaths> possiblePathsList = searchIntermediaryPaths(new ArrayList<>(stationsRequest));
		PossibleSolutionPaths concatenatedPossiblePaths;
		PossibleSolutionPaths previousPossiblePaths = new PossibleSolutionPaths();
		int currentPossiblePathsNumber;
		
		// System.out.println("Big Blob: " + bigBlob.toString() + "\n");
		
		concatenatedPossiblePaths = new PossibleSolutionPaths(possiblePathsList.get(0));
		previousPossiblePaths = new PossibleSolutionPaths(concatenatedPossiblePaths);
		
		for(int i = 1; i < possiblePathsList.size(); i++) {
			PossibleSolutionPaths currentPossiblePaths = possiblePathsList.get(i);
			
			 // System.out.println("Previous Blob: " + previousPossiblePaths);
			 // System.out.println("Current Blob: " + currentPossiblePaths + "\n");
			
			currentPossiblePathsNumber = currentPossiblePaths.getSize();
			concatenatedPossiblePaths = new PossibleSolutionPaths();
			
			for(int j=0; j < currentPossiblePathsNumber; j++) {
				for (Path currentPath: currentPossiblePaths.possibleSolutionPaths) {
					for(Path previousPath: previousPossiblePaths.possibleSolutionPaths) {
						
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
