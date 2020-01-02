package DataStructures;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HourWeather extends WeatherParent {
    @Override
    public String toString() {
        return "Hour:{time:" + getTime() + ",summary:" + getSummary() + ",icon:" + getIcon() + ",temperature:" + getTemperature() + ",humidity:" + getHumidity() + "}";
    }
}
