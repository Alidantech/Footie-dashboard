package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Abstract class designed to be extended. 
 * Provides basic reading functionalities of datasets with player entries and queries.
 *
 * @version 1.1 09/02/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 * @author Ben Clegg
 *
 * Copyright (c) University of Sheffield 2023
 */
public abstract class AbstractPlayerCatalog {

	protected final Map<League, List<PlayerEntry>> playerEntriesMap;

	/**
	 * Constructor - reads datasets with player catalogue (player entries
	 * and initialises the playerEntriesMap Map
     */
    public AbstractPlayerCatalog(String eplFilename, String ligaFilename) {

        playerEntriesMap = new HashMap<>();
        // editPlayerCatalog will read the data and will insert the list of player entries
        // into the playerEntriesMap variable
        editPlayerCatalog(League.EPL, eplFilename);
        editPlayerCatalog(League.LIGA, ligaFilename);

        // updatePlayerCatalog will update playerEntriesMap to contain 'also'
        // an additional list containing all player entries
        // (in this case Premier League and La Liga)
        updatePlayerCatalog();
    }

    public int getNumberPlayerEntries(League league)
    {
        return playerEntriesMap.get(league).size();
    }

    /**
     * Reads the two CSV files and the type passed by main. It then reads the contents of the files
     * and creates the relevant PlayerEntry objects and returns them into a list. Catches exception errors
     * should they occur.
     *
     * @param dataFile This will be the dataset providing data about the league
     * @param league This is a League enum containing the league type
     * @return List of PlayerEntry objects
     */
    public List<PlayerEntry> readDataFile(String dataFile, League league) throws IllegalArgumentException {
        List<PlayerEntry> playerEntriesList = new ArrayList<>();
        int count = 1;
        dataFile = dataFile.replaceAll(" ", "");

        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line = br.readLine();
            if (line == null) {
                throw new IllegalArgumentException("File is empty. Provide a valid dataset.");
            }
            while ((line = br.readLine()) != null) {
                try {
                    // The player entry ID is created by this reader; it is not provided in the original files
                    // The ID should _not_ be modified later
                    int id = count;		
                    PlayerEntry playerEntry = new PlayerEntry(id, league, parsePlayerEntryLine(line));
                    playerEntriesList.add(playerEntry);
                    count++;

                } catch (NumberFormatException e) {
                    System.err.println("File format is incorrect; only double values are allowed.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Malformed player entry line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(dataFile + " could not be found. Provide a correct filename." + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return playerEntriesList;
    }

    /**
     * Parse the properties from a given line from a player catalog file.
     * You can expect that each value appears in the same order as the columns in the file,
     * and that this order will not change.
     *
     * @param line the line to parse
     * @return a PlayerPropertyMap constructed from the parsed row, containing values for every property
     * @throws IllegalArgumentException if the line is malformed (i.e. does not include every property
     * for a single player, or contains undefined properties)
     */
    public abstract PlayerPropertyMap parsePlayerEntryLine(String line) throws IllegalArgumentException;

    /**
     * Updates playerEntriesMap to contain 'also' an additional list
     * containing ALL player entries (in this case Premier League and La Liga)
     * Note: this should not modify the other player entry lists
     */
    public abstract void updatePlayerCatalog();

    /**
     * Read the contents of filename and stores it.
     *
     * @param league indicates which list of player entries to modify
     * @param filename the name of the .csv to read
     */
    public void editPlayerCatalog(League league, String filename){
        playerEntriesMap.put(league, new ArrayList<>(readDataFile(filename, league)));
    }
    
    /**
     * Returns the list of player entries relevant to the specified league.
     *
     * @param league the league type to retrieve
     * @return List<PlayerEntry>, a list of Premier League, La Liga, or ALL Player entries
     */
    public List<PlayerEntry> getPlayerEntriesList(League league) {
        return playerEntriesMap.get(league);
    }

    /**
     * Returns the list of player entries after filtering by PlayerDetail.
     *
     * @param listPlayerEntries the list of player entries used as input for this filtering by PlayerDetail
     * @param playerDetail the PlayerDetail to retrieve
     * @param name the name of the PlayerDetail to retrieve
     * @return List<PlayerEntry>, a </PlayerEntry> list with the relevant player entries
     */
    public List<PlayerEntry> getPlayerEntriesList(List<PlayerEntry> listPlayerEntries,
                                                  PlayerDetail playerDetail,
                                                  String name) {
        if (name.equals("")) return listPlayerEntries;

        List<String> listPlayerNames =
                listPlayerEntries.stream().map(PlayerEntry::getPlayerName).collect(Collectors.toList());
        listPlayerEntries  = listPlayerEntries.stream()
                .filter(w -> w.getDetail(playerDetail).equals(name)).collect(Collectors.toList());

        return listPlayerEntries;
    }

    /**
     * getPlayerEntriesCount method - returns how many player entries of the given type are stored
     *
     * @param league Either Premier League, La Liga or all
     * @return number of player entries held of type <code>league</code>
     */
    public int getPlayerEntriesCount(League league) {
        List<PlayerEntry> list = getPlayerEntriesList(league);
        return list.size(); // list should never be null
    }

    /**
     * getNumberUniquePlayers method - returns how many UNIQUE players of the given type are stored
     *
     * @param league Either Premier League, La Liga or all
     * @return number of UNIQUE players held of type <code>league</code>
     */
    public int getNumberUniquePlayers(League league) {
        List<PlayerEntry> list = getPlayerEntriesList(league);
        List<String> listPlayerNames =
                list.stream().map(PlayerEntry::getPlayerName).distinct().collect(Collectors.toList());
        return listPlayerNames.size(); // listPlayerNames should never be null

    }

    /**
     * Get the minimum value of the given property for player entries in this league in this player catalog
     * @param playerProperty the property to evaluate
     * @param league the League to use
     * @return the minimum value of the property
     */
    public double getMinimumValue(PlayerProperty playerProperty, League league) {
        return getMinimumValue(playerProperty, getPlayerEntriesList(league));
    }

    /**
     * Get the maximum value of the given property for player entries in this league in this player catalog.
     *
     * @param playerProperty the property to evaluate
     * @param league the League to use
     * @return the maximum value of the property
     */
    public double getMaximumValue(PlayerProperty playerProperty, League league) {
        return getMaximumValue(playerProperty, getPlayerEntriesList(league));
    }

    /**
     * Get the mean value of the given property for player entries in this league in this player catalog.
     *
     * @param playerProperty the property to evaluate
     * @param league the League to use
     * @return the mean measurement of the property
     */
    public double getMeanAverageValue(PlayerProperty playerProperty, League league) {
        return getMeanAverageValue(playerProperty, getPlayerEntriesList(league));
    }

    /**
     * Get the minimum value of the given property for the specified player entries.
     * Note: these player entries do not necessarily belong to the current catalog.
     *
     * @param playerProperty the property to evaluate
     * @param playerEntryList the list of player entries to check
     * @return the minimum value of the property present in the specified player entries
     * @throws NoSuchElementException if there are no minimum values for the property, because playerEntryList is invalid
     */
    public abstract double getMinimumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException;

    /**
     * Get the maximum value of the given property for the specified player entries.
     * Note: these player entries do not necessarily belong to the current catalog.
     *
     * @param playerProperty the property to evaluate
     * @param playerEntryList the player entries to check
     * @return the maximum value of the property present in the specified player entries
     * @throws NoSuchElementException if there are no maximum values for the property, because playerEntryList is invalid
     */
    public abstract double getMaximumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException;

    /**
     * Get the mean value of the given property for the specified player entries.
     * Note: these player entries do not necessarily belong to the current catalog.
     *
     * @param playerProperty the property to evaluate
     * @param playerEntryList the player entries to check
     * @return the mean value of the property present in the specified player entries
     * @throws NoSuchElementException if there are no maximum values for the property, because playerEntryList is invalid
     */
    public abstract double getMeanAverageValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException;

    /**
     * Get the first 5 player entries in the given league.
     *
     * @param type the League to get the first player entries
     * @return a List of the first 5 player entries of the given type
     */
    public abstract List<PlayerEntry> getFirstFivePlayerEntries(League type);
}
