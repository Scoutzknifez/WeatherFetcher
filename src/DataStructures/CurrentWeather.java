package DataStructures;

public class CurrentWeather extends WeatherParent {
    public CurrentWeather(long time, String summary, String icon, double temperature) {
        super(time, summary, icon, temperature);
    }

    @Override
    public String toString() {
        return "Current:{time:" + getTime() + ",summary:" + getSummary() + ",icon:" + getIcon() + ",temperature:" + getTemperature() + "}";
    }
}