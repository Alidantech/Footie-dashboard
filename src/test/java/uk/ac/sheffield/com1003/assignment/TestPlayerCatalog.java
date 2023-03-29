package uk.ac.sheffield.com1003.assignment;

import org.junit.jupiter.api.Test;
import uk.ac.sheffield.com1003.assignment.codeprovided.League;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerEntry;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerProperty;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerPropertyMap;
import uk.ac.sheffield.com1003.assignment.common.TestCommon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayerCatalog {

    private static final double DELTA = 0.0001;

    @Test
    public void testUpdateCatalog() {
        PlayerCatalog catalog = new PlayerCatalog(TestCommon.EPL_FILE, TestCommon.LIGA_FILE);

        assertEquals(540, catalog.getPlayerEntriesList(League.EPL).size());
        assertEquals(550, catalog.getPlayerEntriesList(League.LIGA).size());
        assertEquals(540 + 550, catalog.getPlayerEntriesList(League.ALL).size());

        // Should not change if updatePlayerCatalog is called again
        catalog.updatePlayerCatalog();

        assertEquals(540, catalog.getPlayerEntriesList(League.EPL).size());
        assertEquals(550, catalog.getPlayerEntriesList(League.LIGA).size());
        assertEquals(540 + 550, catalog.getPlayerEntriesList(League.ALL).size());
    }

    @Test
    public void testGetMeanAverageValue() {
        PlayerCatalog playerCatalog = new PlayerCatalog(TestCommon.EPL_FILE, TestCommon.LIGA_FILE);

        PlayerPropertyMap ap = new PlayerPropertyMap();
        PlayerPropertyMap bp = new PlayerPropertyMap();

        ap.put(PlayerProperty.ASSISTS, 4);
        bp.put(PlayerProperty.ASSISTS, 6);

        PlayerEntry a = new PlayerEntry(1, League.EPL, ap);
        PlayerEntry b = new PlayerEntry(1, League.EPL, bp);

        List<PlayerEntry> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        assertEquals(5, playerCatalog.getMeanAverageValue(PlayerProperty.ASSISTS, list), DELTA);
    }

}
