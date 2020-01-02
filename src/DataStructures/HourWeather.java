package DataStructures;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HourWeather extends WeatherParent {
    @Override
    public String toString() {
        return "Hour:" + super.toString();
    }
}
