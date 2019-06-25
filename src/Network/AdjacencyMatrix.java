package Network;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix {

	final static int INF = NetworkInterface.INF;
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

	void addEdge(int i, int j) {
		a[i][j] = 1;
		a[j][i] = 1;
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

		for (int i = 0; i < size; i++) {
			List<String> interactingProteinsTemp = new ArrayList<>();
			interactingProteinsTemp = ppiData.proteinSet.get(i).interactingProteins;

			for (int j = 0; j < interactingProteinsTemp.size(); j++) {
				int interactingProteinIndex = ppiData.proteinNames.indexOf(interactingProteinsTemp.get(j));
				if (interactingProteinIndex == -1) {
					// System.out.println("Interacting protein not found; edge not created");
					continue;
				}
				matrix.addEdge(i, interactingProteinIndex);
				matrix.addEdge(interactingProteinIndex, i);
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
