package DataStructures;

import lombok.Getter;
import lombok.Setter;

public abstract class HumidWeather extends WeatherParent {
    @Getter @Setter private double humidity;

    public HumidWeather(String summary, String icon, double temperature, double humidity) {
        super(summary, icon, temperature);
        setHumidity(humidity);
    }
}