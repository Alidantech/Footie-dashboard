package uk.ac.sheffield.com1003.assignment.codeprovided.gui;

/**
 * One of the axis of a radar chart.
 * See AbstractRadarChart for more details.
 *
 * @version 1.0 02/03/2023
 *
 * @author Maria-Cruz Villa-Uriol
 *
 * Copyright (c) University of Sheffield 2023
 */

public class RadarAxisValues
{
    private final double min;
    private final double max;
    private final double average;

    public RadarAxisValues(double min, double max, double average)
    {
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

    public double getAverage()
    {
        return average;
    }

}

