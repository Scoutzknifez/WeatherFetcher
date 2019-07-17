package DataStructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public abstract class WeatherParent {
    private long time;
    private String summary;
    private String icon;
    private double temperature;
}