package ru.skypro.homework.utilities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static long getCurrentTime() {
        long timeDifference = 0;
        SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        obj.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date initial = obj.parse("01-01-1970 00:00:00");
            LocalDateTime time = LocalDateTime.now();
            Date now = Timestamp.valueOf(time);
            timeDifference = now.getTime() - initial.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeDifference;
    }
}
