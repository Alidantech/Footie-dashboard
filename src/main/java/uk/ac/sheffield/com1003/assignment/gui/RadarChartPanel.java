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
 * SKELETON IMPLEMENTATION
 */

public class RadarChartPanel extends AbstractRadarChartPanel
{
   
   

    List<PlayerEntry> filteredPlayerEntriesList;
    List<PlayerProperty> playerRadarChartProperties;
   public static  Map<PlayerProperty, RadarAxisValues> map;
    public RadarChartPanel(AbstractPlayerDashboardPanel parentPanel, AbstractRadarChart radarPlot) {
        super(parentPanel, radarPlot);
        map = radarPlot.getRadarPlotAxesValues();
        playerRadarChartProperties = radarPlot.getPlayerRadarChartProperties();
        filteredPlayerEntriesList = radarPlot.getFilteredPlayerEntries();
    } 

    public void repaintPanel() {
        this.repaint();
        System.out.println(playerRadarChartProperties);
    }
    
    @Override
     protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;
        
      
        // Initialize the labels and data arrays
        String[] labels = new String[map.size()];
        double[][] data = new double[3][map.size()];

        // Iterate over the entries in the map and populate the arrays
        int k = 0;
        for (Map.Entry<PlayerProperty, RadarAxisValues> entry : map.entrySet()) {
            labels[k] = entry.getKey().toString();

            data[0][k] = entry.getValue().getMax()/getMaxValueAxis(entry.getKey());
            data[1][k] = entry.getValue().getAverage()/getMaxValueAxis(entry.getKey());
            data[2][k] =entry.getValue().getMin()/getMaxValueAxis(entry.getKey()); 

            k++;
        }
        
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

        // Set up colors for the polygons
        Color[] colors = {
            new Color(102, 153, 255, 30), // blue with 50% transparency
            new Color(102, 255, 153, 30), // green with 50% transparency
            new Color(255, 102, 102, 30)  // red with 50% transparency
        };
            
        // Draw the polygons
        Color[] borderColors = {new Color(102, 153, 255), new Color(102, 255, 153), new Color(255, 102, 102)};
        for (int i = 0; i < data.length; i++) {
            g2.setColor(colors[i]);
            g2.setStroke(new BasicStroke(3));
            g2.fillPolygon(xPoints[i], yPoints[i], numSides);
            g2.setColor(borderColors[i]);
            g2.drawPolygon(xPoints[i], yPoints[i], numSides);
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

        // Define the colors for the polygons and legend
            Color[] colorsPlayer = {
                new Color(102, 153, 255), // blue with 75% transparency
                new Color(102, 255, 153), // green with 75% transparency
                new Color(255, 102, 102, 191)  // red with 75% transparency
            };
            String [] polygonLabels   = {"maximum", "average", "minimum" };
            // Draw a legend
            int startX = centerX + radius + 30;
            int startY = centerY - (data.length * 20) / 2;
            g2.setFont(new Font("Arial", Font.BOLD, 12)); // Set the font to bold
            for (int i = 0; i < data.length; i++) {
                g2.setColor(colorsPlayer[i]);
                g2.fillOval(startX, startY + i * 20, 10, 10);
                g2.drawString(polygonLabels[i], startX + 20, startY + i * 20 + 10); // Adjust the x-coordinate
            }
          
    }

   
    public static String[] convertEnumArrayToStringArray(List<PlayerProperty> property) {
        if(property!= null){
            String[] result = new String[property.size()];
            for (int i = 0; i < property.size(); i++) {
                result[i] = property.get(i).toString();
            }
            return result;
        }
    return null;
    }
    public static double getMaxValueAxis(PlayerProperty property){
        
        PlayerCatalog playerCatalog = new PlayerCatalog("src/main/resources/epl-2223.csv",
                                                         "src/main/resources/laliga-2223.csv");
        
        return playerCatalog.getMaximumValue(property, League.ALL);

    }
    
   
}
    



