package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.util.NoSuchElementException;

/**
 * Provides a helper enum with constants representing the details of a player entry.
 *
 * @version 1.0  06/04/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 *
 * Copyright (c) University of Sheffield 2023
 */
public enum PlayerDetail {
    PLAYER ("Player's name"),
    NATION ("Player's nation"),
    POSITION ("Position in the pitch"),
    TEAM ("Team’s name");

    private final String detailName;

    PlayerDetail(String pName) { detailName = pName; }

    public String getName() { return this.detailName; }

    /**
     * Convert a name String (e.g. "Nation") to the matching PlayerProperty.
     *
     * @param name the String to convert
     * @return the matching PlayerDetail
     * @throws NoSuchElementException if the String does not match any PlayerDetail
     */
    public static PlayerDetail fromName(String name) throws NoSuchElementException {
        String pName = name.toUpperCase();
        PlayerDetail playerDetail = null;
        try {
            playerDetail = PlayerDetail.valueOf(pName);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("No such detail (" + name + ")!");
        }
        return playerDetail;
    }
}