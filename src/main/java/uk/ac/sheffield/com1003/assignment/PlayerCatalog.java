package uk.ac.sheffield.com1003.assignment;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;

import java.util.*;

public class PlayerCatalog extends AbstractPlayerCatalog {

    public static String eplFilename;

    // Constructor
    public PlayerCatalog(String eplFilename, String ligaFilename) {
        super(eplFilename, ligaFilename);
    }

    // Parses a line from the input file and returns a PlayerPropertyMap
    @Override
    public PlayerPropertyMap parsePlayerEntryLine(String line) throws IllegalArgumentException { 
        String[] tokens = line.split(",");

        if (tokens.length != PlayerDetail.values().length + PlayerProperty.values().length) {
            throw new IllegalArgumentException("Incorrect number of fields in the player entry line: " + line);
        }

        PlayerPropertyMap playerPropertyMap = new PlayerPropertyMap();
        int i = 0;

        // Add player detail fields to the map
        for (PlayerDetail detail : PlayerDetail.values()) {
            playerPropertyMap.putDetail(detail, tokens[i]);
            i++;
        }

        // Add player property fields to the map
        for (PlayerProperty property : PlayerProperty.values()) {
            double value = Double.parseDouble(tokens[i]);
            playerPropertyMap.put(property, value);
            i++;
        }

        return playerPropertyMap;
    }

    // Updates the player catalog with all entries
    @Override
    public void updatePlayerCatalog() {
        List<PlayerEntry> allEntries = new ArrayList<>();
        for (List<PlayerEntry> entries : playerEntriesMap.values()) {
            allEntries.addAll(entries);
        }
        playerEntriesMap.put(League.ALL, allEntries);
    }

    // Returns the minimum value of the given player property from the list of player entries
    @Override
    public double getMinimumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
        double minValue = Double.MAX_VALUE;

        for (PlayerEntry playerEntry : playerEntryList) {
            Object value = playerEntry.getProperty(playerProperty);
            if (value instanceof Double) {
                double doubleValue = (Double) value;
                if (doubleValue < minValue) {
                    minValue = doubleValue;
                }
            }
        }

        if (minValue == Double.MAX_VALUE) {
            throw new NoSuchElementException("No double value found for property " + playerProperty);
        }

        return minValue;
    }

    // Returns the maximum value of the given player property from the list of player entries
    @Override
    public double getMaximumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
        double maxValue = Double.MIN_VALUE;

        for (PlayerEntry playerEntry : playerEntryList) {
            Object value = playerEntry.getProperty(playerProperty);
            if (value instanceof Double) {
                double doubleValue = (Double) value;
                if (doubleValue > maxValue) {
                    maxValue = doubleValue;
                }
            }
        }

        if (maxValue == Double.MAX_VALUE) {
            throw new NoSuchElementException("No double value found for property " + playerProperty);
        }

        return maxValue;
    }

    // Returns the mean average value of the given player property from the list of player entries
    @Override
    public double getMeanAverageValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
    
        double sum = 0.0;
        int count = 0;
        for (PlayerEntry entry : playerEntryList) {
            try {
                double value = entry.getProperty(playerProperty);
                sum += value;
                count++;
            } catch (NoSuchElementException e) {
                            // If the player entry does not have the requested property value, skip it
        }
    }
    if (count == 0) {
        throw new NoSuchElementException("No player entries found with property value: " + playerProperty);
    }
    return sum / count;
    }

    // Returns the first five player entries of the specified league
    @Override
    public List<PlayerEntry> getFirstFivePlayerEntries(League type) {
        List<PlayerEntry> playerEntries = playerEntriesMap.get(type);
        getPlayerEntriesList(type);
        if (playerEntries == null) {
            // Handle case where no entries exist for the given type
            return new ArrayList<>();
        }
        // Return player entries from the specified league
        return playerEntries;
    }

    // Returns the number of unique players in the specified league
    @Override
    public int getNumberUniquePlayers(League league) {
        List<PlayerEntry> list = getPlayerEntriesList(league);
        Set<String> uniquePlayers = new HashSet<>();

        for (PlayerEntry playerEntry : list) {
            String playerName = playerEntry.getPlayerName();
            if (!uniquePlayers.contains(playerName)) {
                uniquePlayers.add(playerName);
            }
        }

        // Returns the total number of unique players
        return uniquePlayers.size();
}
}
