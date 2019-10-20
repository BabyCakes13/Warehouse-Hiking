package search;

import java.util.ArrayList;

public class PossibleSolutionPaths {
	ArrayList<CurrentPath> possiblePaths;
	
	public PossibleSolutionPaths() {
		this.possiblePaths = new ArrayList<CurrentPath>();
	}

	public PossibleSolutionPaths(PossibleSolutionPaths ppObject) {
		this.possiblePaths = new ArrayList<CurrentPath>(ppObject.possiblePaths);
	}
	
	public void add(CurrentPath path) {
		this.possiblePaths.add(path);
	}
	
	public int getSize() {
		return this.possiblePaths.size();
	}

	@Override
	public String toString() {
		String toString = "";
		
		for(CurrentPath path: possiblePaths) {
			toString = toString.concat("\n" + path);
		}
		
		return toString;
	}
	
}
