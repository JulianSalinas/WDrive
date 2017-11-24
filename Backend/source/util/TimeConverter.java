package util;

import java.text.SimpleDateFormat;

public class TimeConverter {

    public static String millisToString(long millis) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
        return DATE_FORMAT.format(millis);
    }

}
