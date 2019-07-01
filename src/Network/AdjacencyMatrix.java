package Network;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix {

	final static int INF = NetworkInterface.INF;
	final static int weightHC = 1;
	final static int weightMC = 3;
	final static int weightLC = 10;
	
	int n;
	int[][] a = {};

	AdjacencyMatrix(int size) {
		n = size;
		a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					a[i][j] = 0;
				} else {
					a[i][j] = 99999;
				}
			}
		}
	}

	void addEdge(int i, int j, int weight) {
		a[i][j] = weight;
		a[j][i] = weight;
	}

	void removeEdge(int i, int j) {
		a[i][j] = 0;
	}

	int hasEdge(int i, int j) {
		return a[i][j];
	}

	public static AdjacencyMatrix buildPPIMatrix(PPIData ppiData) {
		int size = ppiData.proteinSet.size();
		System.out.println("Matrix size: " + size);
		AdjacencyMatrix matrix = new AdjacencyMatrix(size);

		// add edges for LC interactions 		TODO: consolidate redundant code for HC MC LC interactions
		System.out.println("Adding edges for low confidence interactions...");
		for (int i = 0; i < size; i++) {
			List<String> interactionsLC = ppiData.proteinSet.get(i).interactionsLC;

			for (int j = 0; j < interactionsLC.size(); j++) {
				int interactingProteinIndex = ppiData.proteinNames.indexOf(interactionsLC.get(j));
				if (interactingProteinIndex == -1) {
					// System.out.println("No interacting proteins found; edge not created");
					continue;
				}
				matrix.addEdge(i, interactingProteinIndex, weightLC);
				matrix.addEdge(interactingProteinIndex, i, weightLC);
			}
		}
		
		// add edges for MC interactions 		TODO: consolidate redundant code for HC MC LC interactions
		System.out.println("Adding edges for medium confidence interactions...");
		for (int i = 0; i < size; i++) {
			List<String> interactionsMC = ppiData.proteinSet.get(i).interactionsMC;

			for (int j = 0; j < interactionsMC.size(); j++) {
				int interactingProteinIndex = ppiData.proteinNames.indexOf(interactionsMC.get(j));
				if (interactingProteinIndex == -1) {
					// System.out.println("No interacting proteins found; edge not created");
					continue;
				}
				matrix.addEdge(i, interactingProteinIndex, weightMC);
				matrix.addEdge(interactingProteinIndex, i, weightMC);
			}
		}
		
		// add edges for HC interactions 		TODO: consolidate redundant code for HC MC LC interactions
		System.out.println("Adding edges for high confidence interactions...");
		for (int i = 0; i < size; i++) {
			List<String> interactionsHC = ppiData.proteinSet.get(i).interactionsHC;

			for (int j = 0; j < interactionsHC.size(); j++) {
				int interactingProteinIndex = ppiData.proteinNames.indexOf(interactionsHC.get(j));
				if (interactingProteinIndex == -1) {
					// System.out.println("No interacting proteins found; edge not created");
					continue;
				}
				matrix.addEdge(i, interactingProteinIndex, weightHC);
				matrix.addEdge(interactingProteinIndex, i, weightHC);
			}
		}
		
		return matrix;
	}
	
	// print without row/column headers
	void printMatrix() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (a[i][j] == INF)
					System.out.print("0,");
				else
					System.out.print(a[i][j] + ",");
			}
			System.out.println();
		}
	}
	
	//print with row/column headers
	void printMatrix(List<String> proteinNames) {
		if (proteinNames.size() != n) {
			System.out.println("ERROR: list and array sizes do not match.");
			return;
		}
		System.out.print(" ,");
		for (int k = 0; k < n; ++k) {
			System.out.print(proteinNames.get(k) + ",");
		}
		System.out.println();
		for (int i = 0; i < n; ++i) {
			System.out.print(proteinNames.get(i) + ",");
			for (int j = 0; j < n; ++j) {
				if (a[i][j] == INF)
					System.out.print("0,");
				else
					System.out.print(a[i][j] + ",");
			}
			System.out.println();
		}
	}
}
