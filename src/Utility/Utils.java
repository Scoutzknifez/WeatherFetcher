package Utility;

import DataFetcher.FetcherController;

public class Utils {
    public static void initializeApplication() {
        FetcherController.fetchWeather();
    }

    public static boolean isEmptyJsonString(String string) {
        return (string == null || string.equalsIgnoreCase("") || string.equalsIgnoreCase("{}"));
    }
}