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
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public TimeAtMoment(long epoch) {
        Date date = new Date(Utils.getMillisFromEpoch(epoch));
        DateFormat format = new SimpleDateFormat("yyyy:MM:dd:kk:mm:ss");
        String formatted = format.format(date);
        System.out.println(formatted);
    }

    @Override
    public String toString() {
        return getYear() + "/" + getMonth() + "/" + getDay() + " " + getHour() + ":" + getMinute() + ":" + getSecond();
    }
}