package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class EpochConverterUtil {
    // Convert deadline dari format (dd-MM-yyyy HH:mm:ss) ke format epoch
    public static String convertDateToEpoch(String taskDeadline) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(taskDeadline, dateTimeFormatter);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        long epochTime = instant.toEpochMilli() / 1000;
        return String.valueOf(epochTime);
    }

    // Convert deadline dari format epoch ke format (dd-MM-yyyy HH:mm:ss)
    public static String convertEpochToDate(String taskDeadline) {
        long epochTime = Long.parseLong(taskDeadline);
        Date date = new Date(epochTime*1000L);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return dateFormat.format(date);
    }
}
