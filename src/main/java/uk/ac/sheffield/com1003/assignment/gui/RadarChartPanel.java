package uk.ac.sheffield.com1003.assignment.gui;

import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractRadarChart;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractRadarChartPanel;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.RadarAxisValues;
import uk.ac.sheffield.com1003.assignment.PlayerCatalog;
import uk.ac.sheffield.com1003.assignment.codeprovided.League;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerEntry;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerProperty;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractPlayerDashboardPanel;

import java.util.List;
import java.util.Map;

import java.awt.*;

/**
 * Custom radar chart panel for the assignment
 */
public class RadarChartPanel extends AbstractRadarChartPanel {

    // Instance variables
    List<PlayerEntry> filteredPlayerEntriesList;
    List<PlayerProperty> playerRadarChartProperties;
    public static List<Boolean> displayPolygons;
    public static Map<PlayerProperty, RadarAxisValues> map;
    public static String[] labels = new String[5];
    public static double[][] data = new double[3][];

    public RadarChartPanel(AbstractPlayerDashboardPanel parentPanel, AbstractRadarChart radarPlot) {
        super(parentPanel, radarPlot);
        map = radarPlot.getRadarPlotAxesValues();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0, getWidth(), getHeight());
        drawRadarChart(g2);
    }

    // Method to repaint the panel
    public void repaintPanel() {
        this.repaint();
    }

    /**
     * Draws the radar chart
     */
    private void drawRadarChart(Graphics2D g2) {
        Dimension d = getSize();

        // Get the colors for the chart
        Color[][] colors = radarChartColors();
        Color[] colorsLegends = colors[0];
        Color[] polygonFillColors = colors[1];
        Color[] borderColors = colors[2];
        // Determine center point and radius of the chart
        int centerX = d.width / 2;
        int centerY = d.height / 2;
        int radius = Math.min(centerX, centerY) - 20;

        // Calculate points for the polygons
        int numSides = data[0].length;
        int[][] xPoints = new int[data.length][numSides];
        int[][] yPoints = new int[data.length][numSides];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < numSides; j++) {
                double angle = Math.toRadians(360 / numSides * j);
                double value = data[i][j] * radius;
                xPoints[i][j] = (int) (centerX + value * Math.cos(angle));
                yPoints[i][j] = (int) (centerY + value * Math.sin(angle));
            }
        }

        // Draw the polygons of the checked boxes.
        displayPolygons = PlayerDashboardPanel.checked;
        for (int i = 0; i < data.length; i++) {
            if (displayPolygons != null && displayPolygons.get(i)) { // Only draw the polygon if enabled by the user
                g2.setColor(polygonFillColors[i]);
                g2.setStroke(new BasicStroke(5));
                g2.fillPolygon(xPoints[i], yPoints[i], numSides);
                g2.setColor(borderColors[i]);
                g2.drawPolygon(xPoints[i], yPoints[i], numSides);
            }
        }
      
        
        
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));

        //add the labels;
        for (int i = 0; i < numSides; i++) {
            double angle = Math.toRadians(360 / numSides * i);
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            g2.drawLine(centerX, centerY, x, y);
            g2.drawString(labels[i], x + 10, y + 5);
        }
            
            String [] polygonLabels   = {"maximum", "average", "minimum" };
            // Draw a legend
            int legendX = 20; // adjust the x-coordinate as necessary
            int legendY = 20; // adjust the y-coordinate as necessary
            g2.setFont(new Font("Arial", Font.BOLD, 12)); // Set the font to bold
            for (int i = 0; i < data.length; i++) {
                g2.setColor(colorsLegends[i]);
                g2.fillOval(legendX, legendY + i * 20, 10, 10);
                g2.drawString(polygonLabels[i], legendX + 20, legendY + i * 20 + 10); // Adjust the x-coordinate
            }
        
    }

    /*
     * The method below carry the data for the radar chart.
     */

     //add the new data from the user
     public static void addData(Map<PlayerProperty, RadarAxisValues> radarPlotAxesValues) {
        // Add new data to the map
        map = radarPlotAxesValues;
        // Update the data arrays and repaint the chart
       
        updateData();
    }
  

    //updates the data
    private static void updateData() {
       System.out.println("updated radar chart");
        // Initialize the labels and data arrays
       labels = new String[map.size()];
       data = new double[3][map.size()];
         // Iterate over the entries in the map and populate the arrays
         int k = 0;
         for (Map.Entry<PlayerProperty, RadarAxisValues> entry : map.entrySet()) {

             labels[k] = entry.getKey().toString();
     
             data[0][k] = entry.getValue().getMax() / getMaxValueAxis(entry.getKey());
             data[1][k] = entry.getValue().getAverage() / getMaxValueAxis(entry.getKey());
             data[2][k] = entry.getValue().getMin() / getMaxValueAxis(entry.getKey());
             k++;
         }
     }

     //adds the colors for the radar chart
    public Color[][] radarChartColors() {
        Color[] colorsLegends = {
            new Color(102, 153, 255), // blue with 75% transparency
            new Color(102, 255, 153), // green with 75% transparency
            new Color(255, 102, 102) // red with 75% transparency
        };
    
        Color[] polygonFillColors = {
            new Color(102, 153, 255, 30), // blue with 50% transparency
            new Color(102, 255, 153, 30), // green with 50% transparency
            new Color(255, 102, 102, 30) // red with 50% transparency
        };
    
        Color[] borderColors = {
            new Color(102, 153, 255),
            new Color(102, 255, 153),
            new Color(255, 102, 102)
        };
    
        // Return an array of color arrays
        return new Color[][] {colorsLegends, polygonFillColors, borderColors};
    }
    
    //coverts the player properties to a string array
    public static String[] playerPropertyToString(List<PlayerProperty> property) {
        if(property!= null){
            String[] result = new String[property.size()];
            for (int i = 0; i < property.size(); i++) {
                result[i] = property.get(i).toString();
            }
            return result;
        }
    return null;
    }

    /*
     * This method gets the heighst value in a property
     *  across all leagues to set it as the highest value in a given axis
     *  eg. 41yrs for property -> AGE
    */
    public static double getMaxValueAxis(PlayerProperty property){
        String eplFilename = "src/main/resources/epl-2223.csv";
        String ligaFilename = "src/main/resources/laliga-2223.csv" ;
        PlayerCatalog playerCatalog = new PlayerCatalog(eplFilename, ligaFilename );

        return playerCatalog.getMaximumValue(property, League.ALL);

    }

    
    
}
    



