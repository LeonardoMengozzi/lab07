package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month {
        JANUARY(31), 
        FEBRUARY(28), 
        MARCH(31), 
        APRIL(30), 
        MAY(31), 
        JUNE(30), 
        JULY(31), 
        AUGUST(31),
        SEPTEMBER(30), 
        OCTOBER(31), 
        NOVEMBER(30), 
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        public static Month fromString(final String monthName) {
            final String formatted = monthName.toUpperCase();
            Month res = null;
            int monthFind = 0;
            for (final var month : Month.values()) {
                if (month.name().startsWith(formatted)) {
                    res = month;
                    monthFind++;
                }
            }
            if (monthFind == 1) {
                return res;
            }
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(String arg0, String arg1) {
                return Integer.compare(Month.fromString(arg0).days, Month.fromString(arg1).days);
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(String arg0, String arg1) {
                return Month.fromString(arg0).compareTo(Month.fromString(arg1));
            }
        };
    }
}
