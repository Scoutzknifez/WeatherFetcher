import DataFetcher.FetchedData;
import DataStructures.DayWeather;
import Utility.Utils;

public class Main {
    public static void main(String[] args) {
        Utils.initializeApplication();
        debug();
    }
    private static void debug() {
        for(DayWeather d : FetchedData.dayWeathers) {
            System.out.println(d.getHighTemperature() + ":" + d.getLowTemperature());
        }
    }
}