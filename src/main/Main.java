package main;

public class Main {
	
	public static final String EDGES_FILE_PATH = "/home/babycakes/workspace/Basket_Travelling/src/files/edges";
	public static final String PACKETS_FILE_PATH = "/home/babycakes/workspace/Basket_Travelling/src/files/packets";
	public static final String BASKETS_FILE_PATH = "/home/babycakes/workspace/Basket_Travelling/src/files/baskets";	

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
		c.buildGraph(EDGES_FILE_PATH);
		c.buildBaskets(BASKETS_FILE_PATH);
		c.buildPackets(PACKETS_FILE_PATH);
	}

}
