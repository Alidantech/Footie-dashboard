package uk.ac.sheffield.com1003.assignment;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;

import java.util.*;

/**
 * SKELETON IMPLEMENTATION
 */
public class PlayerCatalog extends AbstractPlayerCatalog
{
    /**
     * Constructor
     */
    public PlayerCatalog(String eplFilename, String ligaFilename) {
        super(eplFilename, ligaFilename);

    }

    @Override
    public PlayerPropertyMap parsePlayerEntryLine(String line) throws IllegalArgumentException
    {
        // TODO implement
        return new PlayerPropertyMap();
    }

    @Override
    public void updatePlayerCatalog() {
        // TODO delete next line and implement
       playerEntriesMap.put(League.ALL, new ArrayList<>());
        
    }

    @Override
    public double getMinimumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
        // TODO implement
        return -1;
    }

    @Override
    public double getMaximumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
        // TODO implement
        return -1;
    }

    @Override
    public double getMeanAverageValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
        // TODO implement
        return -1;
    }

    @Override
    public List<PlayerEntry> getFirstFivePlayerEntries(League type)
    {
        // TODO implement
        return new ArrayList<>();
    }

}
