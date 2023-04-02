package uk.ac.sheffield.com1003.assignment.gui;

import uk.ac.sheffield.com1003.assignment.codeprovided.AbstractPlayerCatalog;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerEntry;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerProperty;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractRadarChart;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.RadarAxisValues;


import java.util.*;

/**
 * SKELETON IMPLEMENTATION
 */

public class RadarChart extends AbstractRadarChart
{
   
     
    //instance variables
    List<PlayerProperty> radarChartPlayerProperties = getPlayerRadarChartProperties();
    List<PlayerEntry> filteredPlayerEntriesList = getFilteredPlayerEntries();
    Map<PlayerProperty, RadarAxisValues> map = getRadarPlotAxesValues();

    public RadarChart(AbstractPlayerCatalog playerCatalog, List<PlayerEntry> filteredPlayerEntriesList,
                      List<PlayerProperty> playerRadarChartProperties)
    {
        super(playerCatalog, filteredPlayerEntriesList, playerRadarChartProperties);
    }
    @Override
    public void updateRadarChartContents(List<PlayerProperty> radarChartPlayerProperties,
                                         List<PlayerEntry> filteredPlayerEntriesList) {
        
         //  RadarChartPanel.repaintPanel();
            
            
}
    public void redrawChart(double[][] newData) {
        
    }
    @Override
    public List<PlayerProperty> getPlayerRadarChartProperties() throws NoSuchElementException {
        List<PlayerProperty> propertiesList = this.playerRadarChartProperties;
        
        return propertiesList;
    }

    @Override
    public Map<PlayerProperty, RadarAxisValues> getRadarPlotAxesValues() throws NoSuchElementException {
        Map<PlayerProperty, RadarAxisValues> map = new HashMap<>();

        for(PlayerProperty property :  this.playerRadarChartProperties ){

            double minimum = playerCatalog.getMinimumValue(property, getFilteredPlayerEntries());
            double maximum = playerCatalog.getMaximumValue(property, getFilteredPlayerEntries());
            double average = playerCatalog.getMeanAverageValue(property, getFilteredPlayerEntries());
            map.put(property, new RadarAxisValues(minimum, maximum, average));
        }
       
        return map;
    }

    @Override
    public AbstractPlayerCatalog getPlayerCatalog() {
        return null;
    }

    @Override
    public List<PlayerEntry> getFilteredPlayerEntries() {
      List<PlayerEntry> filterdliList = this.filteredPlayerEntries;
      
      return filterdliList;
    }

}

