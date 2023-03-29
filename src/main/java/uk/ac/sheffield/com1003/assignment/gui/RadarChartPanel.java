package uk.ac.sheffield.com1003.assignment.gui;

import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractRadarChart;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractRadarChartPanel;
import uk.ac.sheffield.com1003.assignment.codeprovided.gui.AbstractPlayerDashboardPanel;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * SKELETON IMPLEMENTATION
 */

public class RadarChartPanel extends AbstractRadarChartPanel
{
    public RadarChartPanel(AbstractPlayerDashboardPanel parentPanel, AbstractRadarChart radarPlot) {
        super(parentPanel, radarPlot);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO remove code below and implement
        super.paintComponent(g);
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;
    
        // Get data for the radar chart
       double[] data = {0.8, 0.6, 0.9, 0.7, 0.5};

        // Determine center point and radius of the chart
        int centerX = d.width / 2;
        int centerY = d.height / 2;
        int radius = Math.min(centerX, centerY) - 20;
    
        // Calculate points for the polygon
        int sides = data.length;
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];
        for (int i = 0; i < sides; i++) {
            double angle = Math.toRadians(360 / sides * i);
            double value = data[i] * radius;
            xPoints[i] = (int) (centerX + value * Math.cos(angle));
            yPoints[i] = (int) (centerY + value * Math.sin(angle));
        }
        // Draw the polygon
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(2));
        g2.drawPolygon(xPoints, yPoints, sides);
    
        // Draw the axis lines
        for (int i = 0; i < sides; i++) {
            double angle = Math.toRadians(360 / sides * i);
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            g2.drawLine(centerX, centerY, x, y);
        }
        System.out.println("getHeight(): " + getHeight());
    }



}

