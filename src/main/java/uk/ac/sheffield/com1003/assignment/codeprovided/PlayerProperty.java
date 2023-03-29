package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.util.NoSuchElementException;

/**
 * Provides a helper enum with constants representing the performance properties of a player entry.
 *
 * @version 1.0  06/04/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 *
 * Copyright (c) University of Sheffield 2023
 */
public enum PlayerProperty {
    AGE ("Player's age"),
    MATCHES ("Matches played"),
    MINUTES ("Minutes played"),
    YELLOWCARDS ("Yellow cards per 90 minutes"),
    REDCARDS ("Red cards per 90 minutes"),
    GOALS ("Total number of goals scored"),
    PKGOALS ("Penalty kick goals per 90 minutes"),
    PKATTEMPTS ("Penalty kicks attempted per 90 minutes"),
    ASSISTS ("Assists per 90 minutes"),
    OWNGOALS ("Own goals per 90 minutes"),
    PASSATTEMPTED ("Passes attempted per 90 minutes"),
    PASSCOMPLETED ("Passes completed per 90 minutes"),
    AERIALSWON ("Aerial challenges won per 90 minutes"),
    AERIALSLOST ("Aerial challenges lost per 90 minutes"),
    AERIALSWONPERC ("Percentage of aerial challenges won per 90 minutes"),
    TACKLES ("Tackles made per 90 minutes"),
    TACKLESWON ("Tackles that regained ball possession per 90 minutes"),
    CLEARANCES ("Clearances made per 90 minutes"),
    FOULSCOMMITTED ("Fouls committed per 90 minutes"),
    PKCONCEDED ("Penalty kicks conceded per 90 minutes"),
    SHOTS ("Shots total per 90 minutes"),
    SHOTSTARGET ("Shots on target per 90 minutes"),
    FOULSDRAWN ("Fouls drawn per 90 minutes"),
    CROSSES ("Crosses made per 90 minutes"),
    PKWON ("Penalty kicks won per 90 minutes");

    private final String propertyName;

    PlayerProperty(String pName) { propertyName = pName; }

    public String getName() { return this.propertyName; }

    /**
     * Convert a name String (e.g. "Matches") to the matching PlayerProperty
     * @param name the String to convert
     * @return the matching PlayerProperty
     * @throws NoSuchElementException if the String does not match any PlayerProperty
     */
    public static PlayerProperty fromName(String name) throws NoSuchElementException {
        String pName = name.toUpperCase();
        PlayerProperty playerProperty = null;
        try {
            playerProperty = PlayerProperty.valueOf(pName);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("No such property (" + name + ")!");
        }
        return playerProperty;
    }
    /**
     * Convert a property name String (e.g. "Matches played") to the matching PlayerProperty
     * @param name the String to convert
     * @return the matching PlayerProperty
     * @throws NoSuchElementException if the String does not match any PlayerProperty
     */
    public static PlayerProperty fromPropertyName(String name) throws NoSuchElementException {
        for (PlayerProperty p : PlayerProperty.values()) {
            if (p.getName().equals(name))
                return p;
        }
        throw new NoSuchElementException("No such property (" + name + ")!");
    }
}