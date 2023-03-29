package uk.ac.sheffield.com1003.assignment;

import org.junit.jupiter.api.Test;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerProperty;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerPropertyMap;
import uk.ac.sheffield.com1003.assignment.common.TestCommon;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileParsing
{
    // TODO Make a test that validates IDs, types and data are correct for the example dataset

    private static final double DELTA = 0.0001;

    @Test
    public void testParseFileLine() {
        PlayerCatalog playerCatalog = new PlayerCatalog(TestCommon.EPL_FILE, TestCommon.LIGA_FILE);

        String sampleLine = "Jose Rojas,BOL,MFFW,Sheffield Uni,22,20,1596,0.11,0,1,0,0,0.11,0,31," +
                "23.2,0.34,1.19,22.2,1.64,0.51,0.28,0.62,0,1.53,0.28,2.26,2.54,0";
        PlayerPropertyMap map = playerCatalog.parsePlayerEntryLine(sampleLine);

        assertEquals(1596, map.get(PlayerProperty.MINUTES));
        assertEquals(1.5299, map.get(PlayerProperty.SHOTS), DELTA);
        assertEquals(0, map.get(PlayerProperty.PKWON));
    }

    @Test
    public void testParseFileLineTooFewColumns() {
        PlayerCatalog catalog = new PlayerCatalog(TestCommon.EPL_FILE, TestCommon.LIGA_FILE);

        String sampleLine = "Jose Rojas,BOL,MFFW,Sheffield Uni,22,20,1596,0.11,0,1,0,0,0.11,0,31," +
                "23.2,0.34,1.19,22.2,1.64,0.51,0.28,0.62,0,1.53,0.28,2.26,2.54";

        assertThrows(IllegalArgumentException.class, () -> catalog.parsePlayerEntryLine(sampleLine));
    }

}
