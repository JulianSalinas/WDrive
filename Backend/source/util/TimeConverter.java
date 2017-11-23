package util;

import java.text.DateFormat;

public class TimeConverter {

    public static String millisToString(long millis) {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(millis);
    }

}
