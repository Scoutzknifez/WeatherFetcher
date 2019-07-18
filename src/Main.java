import DataFetcher.FetchedData;
import DataStructures.TimeAtMoment;
import Utility.Utils;

public class Main {
    public static void main(String[] args) {
        Utils.initializeApplication();
        debug();
    }
    private static void debug() {
        TimeAtMoment timeAtMoment = new TimeAtMoment(FetchedData.currentWeather.getTime());

        boolean isStillSameDay = true;
        int index = 0;
        while(isStillSameDay) {
            TimeAtMoment hour = new TimeAtMoment(FetchedData.hourWeathers.get(index).getTime());
            isStillSameDay = timeAtMoment.isSameDay(hour);
            index++;
            System.out.println(hour);
        }

        System.out.println((new TimeAtMoment(FetchedData.dayWeathers.get(1).getTime()).getEpoch()) - (new TimeAtMoment(FetchedData.dayWeathers.get(0).getTime()).getEpoch()));
    }
}