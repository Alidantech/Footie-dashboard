package uk.ac.sheffield.com1003.assignment.gui;

import uk.ac.sheffield.com1003.assignment.codeprovided.AbstractPlayerCatalog;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerEntry;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerProperty;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractRadarChart;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.RadarAxisValues;

import java.util.*;

/**
 * RadarChart is a class that extends AbstractRadarChart to create a radar chart
 * representing football player data based on user-selected properties.
 */
public class RadarChart extends AbstractRadarChart {
   
    // Instance variables
    List<PlayerProperty> radarChartPlayerProperties;
    List<PlayerEntry> filteredPlayerEntriesList;
    Map<PlayerProperty, RadarAxisValues> map;
    public static List<Boolean> radarChartPolygons;

    /**
     * Constructor for the RadarChart class.
     * 
     * @param playerCatalog                 The PlayerCatalog containing player data.
     * @param filteredPlayerEntriesList     The list of filtered PlayerEntry objects.
     * @param playerRadarChartProperties    The list of PlayerProperty objects to be displayed on the radar chart.
     */
    public RadarChart(AbstractPlayerCatalog playerCatalog, List<PlayerEntry> filteredPlayerEntriesList,
                      List<PlayerProperty> playerRadarChartProperties) {
        super(playerCatalog, filteredPlayerEntriesList, playerRadarChartProperties);
    }
    
    /**
     * Updates the radar chart with the specified properties and filtered players.
     * 
     * @param radarChartPlayerProperties    The list of PlayerProperty objects to be displayed on the radar chart.
     * @param filteredPlayerEntriesList     The list of filtered PlayerEntry objects.
     */
    public void updateRadarChartContents(List<PlayerProperty> radarChartPlayerProperties,
            List<PlayerEntry> filteredPlayerEntriesList) {
        map = getRadarPlotAxesValues();
        
        if(map != null){
            RadarChartPanel.addData(this.map);
        }
    }
    
    /**
     * Returns the list of PlayerProperty objects for the radar chart.
     * 
     * @return A List<PlayerProperty> containing the properties to be displayed on the radar chart.
     * @throws NoSuchElementException If there are no elements in the list.
     */
    @Override
    public List<PlayerProperty> getPlayerRadarChartProperties() throws NoSuchElementException {
        List<PlayerProperty> propertiesList = this.playerRadarChartProperties;
        
        return propertiesList;
    }

    /**
     * Returns a map of PlayerProperty objects to their corresponding RadarAxisValues.
     * 
     * @return A Map<PlayerProperty, RadarAxisValues> containing the properties and their axis values for the radar chart.
     * @throws NoSuchElementException If there are no elements in the map.
     */
    @Override
    public Map<PlayerProperty, RadarAxisValues> getRadarPlotAxesValues() throws NoSuchElementException {
        Map<PlayerProperty, RadarAxisValues> map = new HashMap<>();
       
        if(this.playerRadarChartProperties!=null){
            map.clear();
            for(PlayerProperty property :  this.playerRadarChartProperties){

                double minimum = playerCatalog.getMinimumValue(property, getFilteredPlayerEntries());
                double maximum = playerCatalog.getMaximumValue(property, getFilteredPlayerEntries());
                double average = playerCatalog.getMeanAverageValue(property, getFilteredPlayerEntries());
                map.put(property, new RadarAxisValues(minimum, maximum, average));
            }
        }
        return map;
    }

    /**
     * Returns the PlayerCatalog associated with the radar chart.
     * 
     * @return An AbstractPlayerCatalog object, or null if not available.
     */
    @Override
    public AbstractPlayerCatalog getPlayerCatalog() {
        return null;
    }
    
    /**
     * Returns the list of filtered PlayerEntry objects for the radar chart.
     * 
     * @return A List<PlayerEntry> containing the filtered player entries to be displayed on the radar chart.
     */
    @Override
    public List<PlayerEntry> getFilteredPlayerEntries() {
        List<PlayerEntry> filterdliList = this.filteredPlayerEntries;
      
        return filterdliList;
    }
}    