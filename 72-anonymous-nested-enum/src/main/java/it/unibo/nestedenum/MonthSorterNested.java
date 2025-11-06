package it.unibo.nestedenum;

import java.util.Comparator;

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

        public Month fromString(String str) throws IllegalArgumentException {
            Boolean appoggio = false;
            int count, a;
            count = 0;
            a = -1;
            str = str.toUpperCase();
            for (int i = 0; i < Month.values().length; i++ ){
                appoggio = Month.values()[i].toString().startsWith(str);
                if(appoggio){
                    count++;
                    a = i;
                }
            }
            if(count > 1)
                throw new IllegalArgumentException("The string specified (" + str + ") is ambiguous");
            else if(count < 1)
                throw new IllegalArgumentException("No month with such name (" + str + ")");
            else
                return Month.values()[a];
             
        }

    }

    static class SortByMonthOrder implements Comparator<String>{

        public int compare(final String a, final String b) {
            int ris = 0;
            Month alfa, beta;
            alfa = Month.JANUARY.fromString(a);
            beta = Month.JANUARY.fromString(b);
            ris = (alfa.ordinal() - beta.ordinal());
            return ris;
            
        }
    }

    static class SortByDate implements Comparator<String>{

        public int compare(final String a, final String b) {
            int ris = 0;
            Month alfa, beta;
            alfa = Month.JANUARY.fromString(a);
            beta = Month.JANUARY.fromString(b);
            ris = (alfa.getDays() - beta.getDays());
            return ris;
            
        }
    }


    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
