package _4kyu;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class HumanReadableDurationFormatTest {
    @Test
    public void basicTests() {
        assertEquals("1 second", HumanReadableDurationFormat.formatDuration(1));
        assertEquals("1 minute and 2 seconds", HumanReadableDurationFormat.formatDuration(62));
        assertEquals("2 minutes", HumanReadableDurationFormat.formatDuration(120));
        assertEquals("1 hour", HumanReadableDurationFormat.formatDuration(3600));
        assertEquals("1 hour, 1 minute and 2 seconds", HumanReadableDurationFormat.formatDuration(3662));
        assertEquals("now", HumanReadableDurationFormat.formatDuration(0));
        assertEquals("182 days, 1 hour, 44 minutes and 40 seconds", HumanReadableDurationFormat.formatDuration(15731080));
        assertEquals("4 years, 68 days, 3 hours and 4 minutes", HumanReadableDurationFormat.formatDuration(132030240));
        assertEquals("6 years, 192 days, 13 hours, 3 minutes and 54 seconds", HumanReadableDurationFormat.formatDuration(205851834));
        assertEquals("8 years, 12 days, 13 hours, 41 minutes and 1 second", HumanReadableDurationFormat.formatDuration(253374061));
        assertEquals("7 years, 246 days, 15 hours, 32 minutes and 54 seconds", HumanReadableDurationFormat.formatDuration(242062374));
        assertEquals("3 years, 85 days, 1 hour, 9 minutes and 26 seconds", HumanReadableDurationFormat.formatDuration(101956166));
        assertEquals("1 year, 19 days, 18 hours, 19 minutes and 46 seconds", HumanReadableDurationFormat.formatDuration(33243586));
    }

    private enum Duration {

        YEAR(31536000),
        DAY(86400),
        HOUR(3600),
        MINUTE(60),
        SECOND(1);
        public final int secsInUnit;

        private Duration(int secs) {
            secsInUnit = secs;
        }

        public int valueOf(int seconds) {
            return seconds / secsInUnit;
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }

        public String toFormattedString(int units) {
            return String.format("%d %s", units, toString() + (units > 1 ? "s" : ""));
        }
    }

    private String sol(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Argument must be an int >= 0");
        }
        StringBuilder output = new StringBuilder(50);
        Duration[] durationUnits = Duration.values();
        int[] units = new int[durationUnits.length]; // values of years, days, hours, minutes
        ArrayList<String> formattedUnits = new ArrayList<>(durationUnits.length);
        if (seconds == 0) {
            return "now";
        }
        for (int i = 0; i < durationUnits.length; i++) {
            if (seconds >= durationUnits[i].secsInUnit) {
                units[i] = durationUnits[i].valueOf(seconds);
                seconds %= durationUnits[i].secsInUnit;
                if (units[i] > 0) {
                    formattedUnits.add(durationUnits[i].toFormattedString(units[i]));
                }
            }
        }
        output.append(formattedUnits.get(0));
        if (formattedUnits.size() == 1) {
            return output.toString();
        }
        for (int i = 1; i < formattedUnits.size(); i++) {
            if (i < formattedUnits.size() - 1) {
                output.append(", ");
            }
            else {
                output.append(" and ");
            }
            output.append(formattedUnits.get(i));
        }
        return output.toString();
    }

    @Test
    public void randomTests() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int n = r.nextInt(10000000);
            assertEquals(sol(n), HumanReadableDurationFormat.formatDuration(n));
        }
    }
}
