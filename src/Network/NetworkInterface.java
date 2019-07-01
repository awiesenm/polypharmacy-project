package Network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class NetworkInterface {

	final static int INF = 99999;

	public static void main(String[] args) {

		//read PPI data from CSV
		PPIData ppiData = CSVReader.readPPIFromCSV("resources/inputs/PPI_ubuntu.csv");
		
		//create adjacency matrix
		AdjacencyMatrix adjacencyMatrix = AdjacencyMatrix.buildPPIMatrix(ppiData);
		
		PrintStream stdout = System.out;
		System.out.print("Starting to write 'adj_matrix_all.csv'...   ");
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream("resources/networks/adj_matrix_all.csv"));
			System.setOut(out);
			
			//print adjacency matrix
			adjacencyMatrix.printMatrix(ppiData.proteinNames);
		} catch (IOException e1) {
			System.out.println("Error writing output file.");
		}
		System.setOut(stdout);
		System.out.println("Finished writing 'adj_matrix_all.csv'.");
		System.out.print("Starting to write 'shortest_path_all.csv'...   ");

		
		//create shortest path lookup table
		AllPairShortestPath shortestPaths = new AllPairShortestPath();
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream("resources/networks/shortest_path_all.csv"));
			System.setOut(out);
			
			//run shortest path algorithm and print
			shortestPaths.floydWarshall(adjacencyMatrix, ppiData.proteinNames);
		} catch (IOException e1) {
			System.out.println("Error writing output file.");
		}
		System.setOut(stdout);
		System.out.println("Finished writing 'shortest_path_all.csv'.");
		
		
		
		
//		try {
//			PrintStream out = new PrintStream(new FileOutputStream("ProteinIndexes.txt"));
//			System.setOut(out);
//			
//			//print protein indexes
//			
//		} catch (IOException e1) {
//			System.out.println("Error writing output file.");
//		}
		
		
		System.out.println("Network creation complete!");

	}
}
