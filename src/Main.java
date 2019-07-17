import DataFetcher.FetchedData;
import DataStructures.DayWeather;
import Utility.Utils;

public class Main {
    public static void main(String[] args) {
        Utils.initializeApplication();
        debug();
    }
    private static void debug() {
        System.out.println(FetchedData.currentWeather);
        System.out.println(FetchedData.hourWeathers.size());
        for(DayWeather d : FetchedData.dayWeathers) {
            System.out.println(d);
        }
    }
}