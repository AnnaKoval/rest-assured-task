package common;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateController {

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
