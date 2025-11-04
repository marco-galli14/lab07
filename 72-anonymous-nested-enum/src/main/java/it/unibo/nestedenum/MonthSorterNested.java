package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

import javax.naming.directory.InvalidAttributesException;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    static enum Month {
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30),
        MAY(31), JUNE(30), JULY(31), AUGUST(31),
        SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

        private final int days;

        private Month (final int days) {
            this.days = days;
        }

        public int getDays() {
            return this.days;
        }

        public Month fromString(String str) throws InvalidAttributesException {
            Boolean appoggio = false;
            int count, a;
            count = 0;
            a = -1;
            str = str.toUpperCase();
            for (int i = 0; i < Month.values().length; i++ ){
                appoggio = Month.values()[i].name().contains(str);
                if(appoggio)
                    count++;
                    a = i;
            }
            if(count > 1)
                throw new InvalidAttributesException("The string specified (" + str + ") is ambiguous");
            else if(count < 1)
                throw new InvalidAttributesException("No month with such name (" + str + ")");
            else
                return Month.values()[a];
             
        }

    }

    static class SortByMonthOrder implements Comparator<String>{

        public int compare(final String a, final String b) {
            int ris;
            return ris;
        }
    }

    static class SortByDate implements Comparator<String>{

    }


    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}
