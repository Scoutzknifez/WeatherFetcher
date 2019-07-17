package DataStructures;

import lombok.Getter;
import lombok.Setter;

public class DayWeather extends HumidWeather {
    @Getter @Setter private HourWeather[] hourlyWeather = new HourWeather[24];

    public DayWeather(long time, String summary, String icon, double temperature, double humidity) {
        super(time, summary, icon, temperature, humidity);
    }

    public double getHighTemperature() {
        return 0;
    }

    public double getLowTemperature() {
        return 0;
    }

    public String toString() {
        return "Day:{time:" + getTime() + ",summary:" + getSummary() + ",icon:" + getIcon() + ",temperature:" + getTemperature() + ",humidity:" + getHumidity() + "}";
    }
}