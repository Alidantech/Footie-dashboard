package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.util.NoSuchElementException;

/**
 * Provides a helper enum with constants representing which player properties map to each category.
 * These will be used by RadarChart.
 *
 * @version 1.0  06/04/2023
 *
 * @author Maria-Cruz Villa-Uriol (m.villa-uriol@sheffield.ac.uk)
 *
 * Copyright (c) University of Sheffield 2023
 */
public enum Category {
    GENERAL("general") {
        @Override
        public PlayerProperty[] getProperties() {
            return new PlayerProperty[]{PlayerProperty.AGE, PlayerProperty.MATCHES,
                    PlayerProperty.MINUTES, PlayerProperty.YELLOWCARDS,
                    PlayerProperty.REDCARDS};
        }
    },
    GOALS("goals") {
        @Override
        public PlayerProperty[] getProperties() {
            return new PlayerProperty[]{PlayerProperty.GOALS, PlayerProperty.PKGOALS,
                    PlayerProperty.PKATTEMPTS, PlayerProperty.ASSISTS,
                    PlayerProperty.OWNGOALS};
        }
    },
    PLAY("play") {
        @Override
        public PlayerProperty[] getProperties() {
            return new PlayerProperty[]{PlayerProperty.PASSATTEMPTED, PlayerProperty.PASSCOMPLETED,
                    PlayerProperty.AERIALSWON, PlayerProperty.AERIALSLOST,
                    PlayerProperty.AERIALSWONPERC};
        }
    },
    DEFENCE("defence") {
        @Override
        public PlayerProperty[] getProperties() {
            return new PlayerProperty[]{PlayerProperty.TACKLES, PlayerProperty.TACKLESWON,
                    PlayerProperty.CLEARANCES, PlayerProperty.FOULSCOMMITTED,
                    PlayerProperty.PKCONCEDED};
        }
    },
    ATTACK("attack") {
        @Override
        public PlayerProperty[] getProperties() {
            return new PlayerProperty[]{PlayerProperty.SHOTS, PlayerProperty.SHOTSTARGET,
                    PlayerProperty.FOULSDRAWN, PlayerProperty.CROSSES,
                    PlayerProperty.PKWON};
        }
    };

    private final String categoryName;

    Category(String catName) { categoryName = catName; }

    public String getName()
    {
        return categoryName;
    }
    public abstract PlayerProperty[] getProperties();

    /**
     * Convert an identifier String (e.g. "general") to the matching Category.
     *
     * @param name the identifier to convert
     * @return the matching Category
     * @throws NoSuchElementException if the String does not match any Category
     */
    public static Category getCategoryFromName(String name) throws NoSuchElementException
    {
        for (Category cat : values()) {
            if (cat.getName().equals(name))
                return cat;
        }
        throw new NoSuchElementException("No such category (" + name + ")!");
    }

}