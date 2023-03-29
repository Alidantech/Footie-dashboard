package uk.ac.sheffield.com1003.assignment.common;

import uk.ac.sheffield.com1003.assignment.codeprovided.League;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerEntry;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerProperty;
import uk.ac.sheffield.com1003.assignment.codeprovided.PlayerPropertyMap;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TestCommon
{

    // File input, you should _not_ modify these files or create new ones
    public static final String EPL_FILE = "src/main/resources/epl-2223.csv";
    public static final String LIGA_FILE = "src/main/resources/laliga-2223.csv";


    public static final List<PlayerEntry> DUMMY_PLAYER_ENTRIES = Arrays.asList(
            new PlayerEntry(0, League.EPL, new PlayerPropertyMapBuilder()
                .with(PlayerProperty.ASSISTS, 7.4)
                .build()),
            new PlayerEntry(1, League.EPL, new PlayerPropertyMapBuilder()
                .with(PlayerProperty.ASSISTS, 7.8)
            .build())
    );

    /**
     * Helper method to tokenize a String, by splitting a String by the presence of
     * whitespace
     *
     * @param toTokenize the String to tokenize.
     * @return A list of token Strings
     */
    public static List<String> tokenizeString(String toTokenize) {
        List<String> tokens;
        toTokenize = toTokenize.replaceAll(Pattern.compile("\\s+").pattern(), " ");
        tokens = Arrays.asList(toTokenize.split(Pattern.compile(" ").pattern()));
        return tokens;
    }

    /**
     * Helper method to split a multi-line String into a List of Strings by the
     * newline character (\n).
     *
     * @param toSplit the String to split into a series of lines.
     * @return A list of individual lines.
     */
    @SuppressWarnings("unused")
    public static List<String> getLines(String toSplit) {
        List<String> lines;
        lines = Arrays.asList(toSplit.split(Pattern.compile("\n").pattern()));
        return lines;
    }


    /**
     * Class to manually create a PlayerPropertyMap
     * Not necessarily recommended for normal use, but it can be useful for tests
     */
    private static class PlayerPropertyMapBuilder {

        final PlayerPropertyMap map = new PlayerPropertyMap();

        PlayerPropertyMapBuilder with(@SuppressWarnings("SameParameterValue") PlayerProperty playerProperty, double value) {
            this.map.put(playerProperty, value);
            return this;
        }

        PlayerPropertyMap build() {
            return map;
        }
    }
}
