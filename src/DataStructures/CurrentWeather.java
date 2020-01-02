package DataStructures;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CurrentWeather extends WeatherParent {
    @Override
    public String toString() {
        return "Current:{time:" + getTime() + ",summary:" + getSummary() + ",icon:" + getIcon() + ",temperature:" + getTemperature() + "}";
    }
}
