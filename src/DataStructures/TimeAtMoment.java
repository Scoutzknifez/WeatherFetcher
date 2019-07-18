package DataStructures;

import Utility.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor @Getter @Setter
public class TimeAtMoment {
    private long epoch;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public TimeAtMoment(long epoch) {
        setEpoch(epoch);
        Date date = new Date(Utils.getMillisFromEpoch(epoch));
        DateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        String formatted = format.format(date);
        String[] splits = formatted.split(":");
        try {
            year = Integer.parseInt(splits[0]);
            month = Integer.parseInt(splits[1]);
            day = Integer.parseInt(splits[2]);
            hour = Integer.parseInt(splits[3]);
            minute = Integer.parseInt(splits[4]);
            second = Integer.parseInt(splits[5]);
        } catch (Exception e) {
            System.out.println("Can not properly create TimeAtMoment object.");
        }
    }

    public boolean isSameDay(TimeAtMoment compareTo) {
        return compareTo.getDay() == getDay();
    }

    @Override
    public String toString() {
        return getYear() + "/" + getMonth() + "/" + getDay() + " " + getHour() + ":" + getMinute() + ":" + getSecond();
    }
}