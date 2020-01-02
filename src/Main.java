import DataFetcher.FetchedData;
import DataStructures.DayWeather;
import Utility.Utils;

public class Main {
    public static void main(String[] args) {
        Utils.initializeApplication();

        System.out.println(FetchedData.currentWeather);

        for (DayWeather day : FetchedData.dayWeathers)
            System.out.println(day.toString());
    }
}
