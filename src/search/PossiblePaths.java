package search;

import java.util.ArrayList;

public class PossiblePaths {
	ArrayList<Path> possiblePaths;
	
	public PossiblePaths() {
		this.possiblePaths = new ArrayList<Path>();
	}

	public PossiblePaths(PossiblePaths ppObject) {
		this.possiblePaths = new ArrayList<Path>(ppObject.possiblePaths);
	}
	
	public void add(Path path) {
		this.possiblePaths.add(path);
	}
	
	public int getSize() {
		return this.possiblePaths.size();
	}

	@Override
	public String toString() {
		String toString = "";
		
		for(Path path: possiblePaths) {
			toString = toString.concat("\n" + path);
		}
		
		return toString;
	}
	
}
