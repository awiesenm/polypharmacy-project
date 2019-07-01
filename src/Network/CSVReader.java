package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVReader {

	public static PPIData readPPIFromCSV(String fileName) {
		PPIData ppiData = new PPIData();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile)) {

			String line = br.readLine();
			line = br.readLine();

			while (line != null) {

				String[] rowContents = line.split(";");
				// System.out.println(row_contents[0]);
				PPIProtein protein = new PPIProtein();
				for (int i = 0; i < rowContents.length; i++) {
					if (i == 0) {
						protein.proteinName = rowContents[i];
						ppiData.proteinNames.add(protein.proteinName);
					} else {
						String cell = rowContents[i];
						String[] cellContents = cell.split(",");
						for (int j = 0; j < cellContents.length; j++) {
							if (i == 1) {
								protein.interactionsHC.add(cellContents[j]);
							} else if (i == 2) {
								protein.interactionsMC.add(cellContents[j]);
							} else if (i == 3) {
								protein.interactionsLC.add(cellContents[j]);
							} else {
								System.out.println("Error recording interacting proteins");
							}
						}
					}
				}
				ppiData.proteinSet.add(protein);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return ppiData;
	}

	public static ArrayList<String> readNamesFromCSV(String fileName) {
		ArrayList<String> proteinNames = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile)) {

			String line = br.readLine();
			line = br.readLine();

			while (line != null) {

				String[] row_contents = line.split(",");
				if (row_contents[0] == " ") {
					line = br.readLine();
					continue;
				}
				proteinNames.add(row_contents[0]);
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return proteinNames;
	}

//	public static AdjacencyMatrix buildMatrixFromCSV(String fileName) {
//		AdjacencyMatrix matrix = new AdjacencyMatrix();
//		
//		return 
//	}

}
