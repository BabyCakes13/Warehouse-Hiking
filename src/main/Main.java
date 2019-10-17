package main;

import files.Constants;

public class Main {

	public static void main(String[] args) {
		ObjectBuilder c = new ObjectBuilder();
		setItems(c);
		mainLoop(c);
	}

	public static void mainLoop(ObjectBuilder c) {
		InputManager im = new InputManager(c);
		im.askInput();

		// Searcher search = new Searcher(c);
		// ArrayList<PossiblePaths> possiblePathsSequence =
		// search.searchIntermediaryPaths(new ArrayList<>(Arrays.asList(0, 7, 49,
		// 100)));
		// search.searchAllPaths(possiblePathsSequence);
	}

	public static void setItems(ObjectBuilder c) {

		c.buildGraph(Constants.EDGES_FILE_PATH);
		c.buildBaskets(Constants.BASKETS_FILE_PATH);
		c.buildPackets(Constants.PACKETS_FILE_PATH);
	}

}
