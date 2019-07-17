import DataFetcher.FetchedData;
import DataStructures.DayWeather;
import DataStructures.TimeAtMoment;
import Utility.Utils;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Utils.initializeApplication();
        debug();
    }
    private static void debug() {
        TimeAtMoment timeAtMoment = new TimeAtMoment(FetchedData.currentWeather.getTime());
    }
}