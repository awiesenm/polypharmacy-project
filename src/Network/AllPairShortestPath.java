package Network;

import java.util.List;

public class AllPairShortestPath {

	final static int INF = NetworkInterface.INF;

	void floydWarshall(AdjacencyMatrix inputMatrix, List<String> proteinNames) {
		int a[][] = inputMatrix.a;
		int n = inputMatrix.n;
		int dist[][] = new int[n][n];
		int i, j, k;

		/*
		 * Initialize the solution matrix same as input graph matrix. Or we can say the
		 * initial values of shortest distances are based on shortest paths considering
		 * no intermediate vertex.
		 */
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				dist[i][j] = a[i][j];

		/*
		 * Add all vertices one by one to the set of intermediate vertices. ---> Before
		 * start of an iteration, we have shortest distances between all pairs of
		 * vertices such that the shortest distances consider only the vertices in set
		 * {0, 1, 2, .. k-1} as intermediate vertices. ----> After the end of an
		 * iteration, vertex no. k is added to the set of intermediate vertices and the
		 * set becomes {0, 1, 2, .. k}
		 */
		for (k = 0; k < n; k++) {
			// Pick all vertices as source one by one
			for (i = 0; i < n; i++) {
				// Pick all vertices as destination for the
				// above picked source
				for (j = 0; j < n; j++) {
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}

		// Print the shortest distance matrix
		printSolution(dist, n, proteinNames);
	}

	void printSolution(int dist[][], int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dist[i][j] == INF)
					System.out.print("~,");
				else
					System.out.print(dist[i][j] + ",");
			}
			System.out.println();
		}
	}
	
	void printSolution(int dist[][], int n, List<String> proteinNames) {
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
				if (dist[i][j] == INF)
					System.out.print("~,");
				else
					System.out.print(dist[i][j] + ",");
			}
			System.out.println();
		}
	}
}
