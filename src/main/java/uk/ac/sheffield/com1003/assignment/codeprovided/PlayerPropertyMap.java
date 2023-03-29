package uk.ac.sheffield.com1003.assignment.codeprovided;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A simplified Map wrapper to be used by a PlayerEntry, storing the values for each of its properties.
 * This wrapper stores a HashMap rather than extending it to de-expose unneeded methods.
 * Copyright (c) University of Sheffield 2023
 *
 * @version 1.0 10/02/2023
 *
 * @author Ben Clegg
 */
public class PlayerPropertyMap {

    // Consult the Javadocs for Map & HashMap for more information
    private final Map<PlayerProperty, Double> propertyToValuesMap = new HashMap<>();

    // Consult the Javadocs for Map & HashMap for more information
    private final Map<PlayerDetail, String> detailsMap = new HashMap<>();


    /**
     * Add a PlayerProperty and value pair. See HashMap.put() for more technical details.
     * @param playerProperty the property to store
     * @param value the value associated with the property
     */
    public void put(PlayerProperty playerProperty, double value) {
        propertyToValuesMap.put(playerProperty, value);
    }

    /**
     * Add a PlayerDetail and value pair. See HashMap.put() for more technical details.
     * @param playerDetail the detail to store
     * @param detail the value associated with the property
     */
    public void putDetail(PlayerDetail playerDetail, String detail) {
        detailsMap.put(playerDetail, detail);
    }


    /**
     * Retrieve a value associated with a given PlayerProperty. See HashMap.get() for more technical details.
     * @param playerProperty the PlayerProperty to retrieve the value of
     * @return the retrieved value
     */
    public double get(PlayerProperty playerProperty) {
        return propertyToValuesMap.get(playerProperty);
    }

    /**
     * Retrieve a detail associated with a given PlayerDetail. See HashMap.get() for more technical details.
     * @param playerDetail the PlayerDetail to retrieve the value of
     * @return the retrieved value
     */
    public String getDetail(PlayerDetail playerDetail) {
        return detailsMap.get(playerDetail);
    }

    /**
     * Get the properties stored in the map. See HashMap.keySet() for more technical details.
     * @return the properties stored in the map
     */
    public Set<PlayerProperty> propertySet() {
        return propertyToValuesMap.keySet();
    }

}
