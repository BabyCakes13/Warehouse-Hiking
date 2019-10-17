package files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which handles the files which contain the basket, warehouse and packets
 * information. It takes care of reading and extracting the information from the
 * files.
 * 
 * @author babycakes
 *
 */
public class FileManager {
	String filePath;
	Map<Integer, ArrayList<Integer>> dataExtracted;

	/**
	 * Constructor of FileManager class. Initialises the filePath of the manager and
	 * creates a Map oject which will hold data.
	 * 
	 * @param filePath String: Full file path to the file which needs extraction.
	 */
	public FileManager(String filePath) {
		this.filePath = filePath;
		this.dataExtracted = new HashMap<>();
	}

	/**
	 * Method which reads the file line by line, and extracts each row as a Map
	 * pair.
	 * 
	 * @return Map<integer, ArrayList<Integer>>: data from the file as key and
	 *         multiple values.
	 */
	public Map<Integer, ArrayList<Integer>> extractData() {
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

		return this.dataExtracted;
	}

	/**
	 * Method which takes a line from the input file and extracts its information.
	 * The line is treated as: first element on the line is key, the rest elements
	 * are an array of values. If the key exists, the values will be added to the
	 * list of the original key.
	 * 
	 * @param line String: One line read from the file.
	 * @return False: if the line has an eronated number of items, True: if the
	 *         operation completed sucesfully.
	 */
	private boolean addItemToMap(String line) {
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

	/**
	 * Method which prints the extracted data from the file, as key and array of
	 * values.
	 */
	public void printDataExtracted() {
		this.dataExtracted.forEach((key, value) -> System.out.println(key + ": " + value));
	}
}
