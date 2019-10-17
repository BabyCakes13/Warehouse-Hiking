package files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
	String filePath;
	Map<Integer, ArrayList<Integer>> dataExtracted;

	public FileManager(String filePath) {
		this.filePath = filePath;
		this.dataExtracted = new HashMap<>();
	}

	public Map<Integer, ArrayList<Integer>> getDataExctracted() {
		this.extractData();
		return this.dataExtracted;
	}

	public void extractData() {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line;

			while ((line = reader.readLine()) != null) {
				this.addItemToMap(line);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File could not be opened.");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Could not close file.");
				e.printStackTrace();
			}
		}
	}

	public boolean addItemToMap(String line) {
		String[] splitLine = line.split(" ");
		int key;
		ArrayList<Integer> value = new ArrayList<>();
		
		if (splitLine.length < 2) {
			// System.out.println("Line invalid, moving on to next line.");
			return false;
		}

		key = Integer.parseInt(splitLine[0]);

		for (int i = 1; i < splitLine.length; i++) {
			value.add(Integer.parseInt(splitLine[i]));
		}
		
		if (this.dataExtracted.containsKey(key)) {
			ArrayList<Integer> existingValue = this.dataExtracted.get(key);
			value.addAll(existingValue);
		} 
		
		this.dataExtracted.put(key, value);

		return true;
	}

	public void printDataExtracted() {
		this.dataExtracted.forEach((key, value) -> System.out.println(key + ": " + value));
	}
}
