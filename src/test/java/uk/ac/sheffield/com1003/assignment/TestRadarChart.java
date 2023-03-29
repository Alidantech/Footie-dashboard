package uk.ac.sheffield.com1003.assignment;

import org.junit.jupiter.api.Test;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerProperty;
import uk.ac.sheffield.com1003.assignment.common.TestCommon;
import uk.ac.sheffield.com1003.assignment.gui.RadarChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRadarChart
{
    private static final PlayerCatalog CATALOG = new PlayerCatalog(
            TestCommon.EPL_FILE,
            TestCommon.LIGA_FILE
    );

    @Test
    public void testUpdateRadarChart() {
        List<PlayerProperty> properties = Collections.singletonList(PlayerProperty.ASSISTS);
        RadarChart chart = new RadarChart(CATALOG, TestCommon.DUMMY_PLAYER_ENTRIES, properties);
        chart.updateRadarChartContents(properties, new ArrayList<>());
        assertTrue(chart.getRadarPlotAxesValues().isEmpty());
    }

    @Test
    public void testUpdateRadarChartSameData() {
        List<PlayerProperty> properties = Collections.singletonList(PlayerProperty.ASSISTS);
        RadarChart radarChart = new RadarChart(CATALOG, TestCommon.DUMMY_PLAYER_ENTRIES, properties);
        radarChart.updateRadarChartContents(properties, Arrays.asList(
                TestCommon.DUMMY_PLAYER_ENTRIES.get(0),
                TestCommon.DUMMY_PLAYER_ENTRIES.get(0),
                TestCommon.DUMMY_PLAYER_ENTRIES.get(0)
        ));

        assertEquals(1, radarChart.getRadarPlotAxesValues().size());
        assertEquals(3, radarChart.getFilteredPlayerEntries().size());
    }

}
