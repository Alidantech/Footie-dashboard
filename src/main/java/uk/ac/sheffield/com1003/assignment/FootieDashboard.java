package uk.ac.sheffield.com1003.assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractPlayerDashboardPanel;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.PlayerEntryDashboard;
import uk.ac.sheffield.com1003.assignment.gui.PlayerDashboardPanel;

/**
 * Main class to run the Assignment's graphical user interface
 *
 * @version 1.1  09/03/2023
 *
 * @author Maria-Cruz Villa-Uriol
 * @author Ben Clegg
 *
 * Copyright (c) University of Sheffield 2023
 */

public class FootieDashboard {

	private final AbstractPlayerCatalog playerCatalog;
	private final List<Query> queries;

	/**
	 * Main method - receives two CSV files with the relevant player entries data and a text file
	 * with relevant query data.
	 *
	 * @param args two CSV files and a text file with queries defined by the user
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			args = new String[]{
					"src/main/resources/epl-2223.csv",
					"src/main/resources/laliga-2223.csv",
					"src/main/resources/queries.txt"};
		}
		String eplFile = args[0];
		String ligaFile = args[1];
		String queriesFile = args[2];

		FootieDashboard footieDashboard =
				new FootieDashboard(eplFile, ligaFile, queriesFile);

		footieDashboard.startCLI();
		footieDashboard.startGUI();
	}

	public FootieDashboard(String eplFile, String ligaFile, String queriesFile) {
		playerCatalog = new PlayerCatalog(eplFile, ligaFile);
		List<String> queryTokens = new ArrayList<>(AbstractQueryParser.readQueryFile(queriesFile));
		queries = new ArrayList<>(new QueryParser().readQueries(queryTokens));
	}

	/**
	 * Start the simple CLI based portion of the assignment
	 */
	public void startCLI() {
		// Basic player catalogue information
		printNumberUniquePlayers();
		printQuestionAnswers();
		printFirstFivePlayerEntries();

		// Queries
		printNumberQueries();
		executeQueries();
	}

	/**
	 * Display the number of unique players for whole dataset, and number of player entries
	 * for EPL and LIGA datasets
	 */
	private void printNumberUniquePlayers() {
		System.out.print("The number of unique players in both leagues is: ");
		System.out.println(playerCatalog.getNumberUniquePlayers(League.ALL));
		System.out.println();

		System.out.print("The total number of English Premier League player entries in the dataset is: ");
		System.out.println(playerCatalog.getNumberPlayerEntries(League.EPL));
		System.out.println();

		System.out.print("The total number of La Liga players in the dataset is: ");
		System.out.println(playerCatalog.getNumberPlayerEntries(League.LIGA));
		System.out.println();
	}

	/**
	 * Print the answers to the questions defined in the specification
	 */
	private void printQuestionAnswers() {
		System.out.println("The highest number of assists in the Premier League is " +
				playerCatalog.getMaximumValue(PlayerProperty.ASSISTS, League.EPL));
		System.out.println();

		System.out.println("The highest number of fouls committed in La Liga is " +
				playerCatalog.getMaximumValue(PlayerProperty.FOULSCOMMITTED, League.LIGA));
		System.out.println();

		System.out.println("The average number of shots on target across the Premier League and La Liga is  " +
				playerCatalog.getMeanAverageValue(PlayerProperty.SHOTSTARGET, League.ALL));
		System.out.println();
	}

	/**
	 * Print the number of Queries (not SubQueries) identified in the queries file
	 */
	private void printNumberQueries() {
		// Prints results from queries by calling relevant methods
		System.out.println("In total, " + queries.size() + " queries were found.");
		System.out.println();
	}

	/**
	 * Execute each query; displaying the filtered players for each query
	 */
	private void executeQueries() {
		System.out.println("Executing queries...");

		for (Query query : queries) {
			System.out.println(query.toString() + ":");
			List<PlayerEntry> queryResults = query.executeQuery(playerCatalog);
			printPlayerEntries(queryResults);
			//System.out.println(queryResults);
		}
	}

	/**
	 * Display the provided players
	 * @param playerEntries the player entries to display
	 */
	private void printPlayerEntries(Collection<PlayerEntry> playerEntries) {
		int i = 0;
    for (PlayerEntry w : playerEntries) {
        System.out.println(w.toString());
        i++;
        if (i >= 5) {
            break;
        }
    }
	}

	private void printFirstFivePlayerEntries() {
		System.out.println("\nEnglish Premier League:");
		printPlayerEntries(playerCatalog.getFirstFivePlayerEntries(League.EPL));

		System.out.println("\nLa Liga:");
		printPlayerEntries(playerCatalog.getFirstFivePlayerEntries(League.LIGA));
	}

	/**
	 * Start the GUI of the assignment
	 */
	public void startGUI() {
		// Start GUI
		AbstractPlayerDashboardPanel browserPanel = new PlayerDashboardPanel(playerCatalog);
		PlayerEntryDashboard playerDashboard = new PlayerEntryDashboard(browserPanel);
		playerDashboard.setVisible(true);
	}

}
