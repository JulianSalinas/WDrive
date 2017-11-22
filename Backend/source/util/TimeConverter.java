package util;

import java.util.concurrent.TimeUnit;

public class TimeConverter {

    public static String millisToString(long millis) {
        long hrs = TimeUnit.MILLISECONDS.toHours(millis) % 24;
        long min = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long sec = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        long mls = millis % 1000;
        return String.format("%02d:%02d:%02d:%03d", hrs, min, sec, mls);
    }

}
