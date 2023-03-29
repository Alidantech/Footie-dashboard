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

        Line2D l = new Line2D.Double(
                0,
                0,
                d.width,
                d.height
        );
        g2.draw(l);

        System.out.println("getHeight(): " + getHeight());

    }



}

