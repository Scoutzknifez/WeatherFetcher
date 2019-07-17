package Utility;

public class Constants {
    // Program wide variables
    public static boolean hasInternet = false;

    // Weather Fetching Constants
    public static final String WEB_SERVER = "https://api.darksky.net/forecast/";
    public static final String API_KEY = "948fb757148e84bcba59004551b903ec";
    public static final String additionalArgs = "?exclude=minutely,alerts,flags&extend=hourly";

    // Time
    public static final int MillisInSecond = 1000;
}