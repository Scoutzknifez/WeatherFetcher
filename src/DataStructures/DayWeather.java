package DataStructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@AllArgsConstructor @NoArgsConstructor
public class DayWeather extends WeatherParent {
    @Getter @Setter private HourWeather[] hourlyWeather = new HourWeather[24];

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

    @Override
    public String toString() {
        double[] temps = new double[24];
        for (int i = 0; i < temps.length; i++)
            temps[i] = (getHourlyWeather()[i] != null ? getHourlyWeather()[i].getTemperature() : 0);

        return "Day:{time:" + getTime() + ",summary:" + getSummary() + ",icon:" + getIcon() + ",temperature:" + getTemperature() + ",humidity:" + getHumidity() + ",high:" + getHighTemperature() + ",low:" + getLowTemperature() + ",hourlyTemps:" + Arrays.toString(temps) + "}";
    }
}
