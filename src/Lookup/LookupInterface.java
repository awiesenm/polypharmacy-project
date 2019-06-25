package Lookup;

import java.util.ArrayList;

import Network.CSVReader;

public class LookupInterface {

	public static void main(String[] args) {

		
		ArrayList<String> proteinNames = new ArrayList<>();
		proteinNames = CSVReader.readNamesFromCSV("resources/ShortestPathLookup.csv");
		
		String protein1 = "ACTG1";
		String protein2 = "ATRX";
		
		
				
		
		
	}

}
