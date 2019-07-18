package DataStructures;

import lombok.Getter;
import lombok.Setter;

public class DayWeather extends HumidWeather {
    @Getter @Setter private HourWeather[] hourlyWeather = new HourWeather[24];

    public DayWeather(long time, String summary, String icon, double temperature, double humidity) {
        super(time, summary, icon, temperature, humidity);
    }

    public double getHighTemperature() {
        double high = getTemperature();
        for(HourWeather h : getHourlyWeather()) {
            if(h != null && high < h.getTemperature())
                high = h.getTemperature();
        }
        return high;
    }

    public double getLowTemperature() {
        double low = getTemperature();
        for(HourWeather h : getHourlyWeather()) {
            if(h != null && low > h.getTemperature())
                low = h.getTemperature();
        }
        return low;
    }

    public String toString() {
        return "Day:{time:" + getTime() + ",summary:" + getSummary() + ",icon:" + getIcon() + ",temperature:" + getTemperature() + ",humidity:" + getHumidity() + "}";
    }
}