package uk.ac.sheffield.com1003.assignment.codeprovided;

/**
 * Provides a helper enum with constants representing the accepted leagues.
 *
 * @version 1.0  06/04/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 *
 * Copyright (c) University of Sheffield 2023
 */
public enum League {
    ALL("All leagues"),
    EPL("English Premier League"),
    LIGA("La Liga");

    private final String leagueTypeName;

    League(String lTName) { leagueTypeName = lTName; }

    public String getName()
    {
        return leagueTypeName;
    }

    public static League fromName(String leagueName)
    {
        for (League l : League.values()) {
            if (l.getName().equals(leagueName))
                return l;
        }
        return null;
    }
}