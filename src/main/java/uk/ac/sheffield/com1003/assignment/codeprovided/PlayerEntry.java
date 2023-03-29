package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.util.Iterator;

/**
 * Class designed to be used to create objects from player entries.
 *
 * @version 1.1  09/02/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 * @author Ben Clegg
 *
 * Copyright (c) University of Sheffield 2023
 */
public class PlayerEntry {

    private final int id;
    private final League leagueType;

    private final PlayerPropertyMap playerPropertyMap;

    /**
     * Constructor.
     *
     * @param id an id of the player entry; automatically generated in AbstractPlayerCatalog
     * @param league which league
     * @param playerPropertyMap the values of properties for each player entry (in PlayerCatalog)
     */
    public PlayerEntry(int id, League league, PlayerPropertyMap playerPropertyMap) {
        this.id = id;
        this.leagueType = league;
        this.playerPropertyMap = playerPropertyMap;
    }

    /**
     * Get the value of a given property of the players catalog.
     *
     * @param property the player property to select
     * @return the value of the chosen player property
     */
    public double getProperty(PlayerProperty property) {
        return playerPropertyMap.get(property);
    }

    /**
     * Get the value of a given detail of the player.
     *
     * @param playerDetail the player detail to select
     * @return the value of the chosen player detail
     */
    public String getDetail(PlayerDetail playerDetail) {
        return playerPropertyMap.getDetail(playerDetail);
    }

    public int getId() {
        return this.id;
    }

    public League getLeagueType() {
        return this.leagueType;
    }

    public String getPlayerName() {
        return playerPropertyMap.getDetail(PlayerDetail.PLAYER);
    }
    public String getNation() {
        return playerPropertyMap.getDetail(PlayerDetail.NATION);
    }
    public String getPosition() {
        return playerPropertyMap.getDetail(PlayerDetail.POSITION);
    }
    public String getTeam() {
        return playerPropertyMap.getDetail(PlayerDetail.TEAM);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayerEntry{");

        sb.append("ID=");
        sb.append(getId());
        sb.append(", ");

        sb.append("League=");
        sb.append(getLeagueType().getName());
        sb.append(", ");

        Iterator<PlayerProperty> propertyIterator = playerPropertyMap.propertySet().iterator();
        while (propertyIterator.hasNext()) {
            PlayerProperty p = propertyIterator.next();
            sb.append(p.getName());
            sb.append("=");
            sb.append(getProperty(p));

            if(propertyIterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
