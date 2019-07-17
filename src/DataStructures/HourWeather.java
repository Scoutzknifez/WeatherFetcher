package DataStructures;

public class HourWeather extends HumidWeather{
    public HourWeather(long time, String summary, String icon, double temperature, double humidity) {
        super(time, summary, icon, temperature, humidity);
    }

    @Override
    public String toString() {
        return "Hour:{time:" + getTime() + ",summary:" + getSummary() + ",icon:" + getIcon() + ",temperature:" + getTemperature() + ",humidity:" + getHumidity() + "}";
    }
}