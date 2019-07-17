package DataFetcher;

import DataStructures.CurrentWeather;
import DataStructures.DayWeather;
import DataStructures.HourWeather;

import java.util.ArrayList;

public class FetchedData {
    // Last fetched and parsed data
    public static CurrentWeather currentWeather = null;
    public static ArrayList<HourWeather> hourWeathers = null; // Appears to be always length 169 aka index 0 - 168 inclusive
    public static ArrayList<DayWeather> dayWeathers = null;
}