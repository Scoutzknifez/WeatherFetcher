package DataFetcher;

import DataStructures.CurrentWeather;
import DataStructures.DayWeather;
import DataStructures.HourWeather;
import DataStructures.TimeAtMoment;
import Utility.Constants;
import Utility.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

public class FetcherController {
    private static String lat = "33.513833";
    private static String lon = "-117.103338";
    private static String url = Constants.WEB_SERVER + Constants.API_KEY + "/" + lat + "," + lon + Constants.ADDITIONAL_ARGS;

    public static void fetchWeather() {
        try {
            URL darkSkyURL = new URL(url);
            URLConnection connection = darkSkyURL.openConnection();
            BufferedReader fetched = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String receivedData = fetched.readLine();

            if(Utils.isEmptyJsonString(receivedData))
                return;

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(receivedData);

            // Setting up all the fetched data into objects
            doCurrent(root);
            doHourly(root);
            doDaily(root);

            delegateHours();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doCurrent(JsonNode root) {
        JsonNode currentNode = root.path("currently");
        getCurrentWeather(currentNode);
    }

    private static void getCurrentWeather(JsonNode current) {
        long time = System.currentTimeMillis() / 1000;
        String summary = current.path("summary").asText();
        String icon = current.path("icon").asText();
        double temperature = current.path("temperature").asDouble();

        FetchedData.currentWeather = new CurrentWeather(time, summary, icon, temperature);
    }

    private static void doHourly(JsonNode root) {
        JsonNode hourlyNode = root.path("hourly");
        JsonNode hourlyDataNode = hourlyNode.path("data");
        getHourlyWeather(hourlyDataNode);
    }

    private static void getHourlyWeather(JsonNode hourlyNode) {
        Iterator<JsonNode> hours = hourlyNode.elements();
        ArrayList<HourWeather> hourWeathers = new ArrayList<>();

        while (hours.hasNext()) {
            JsonNode day = hours.next();

            long time = day.path("time").asLong();
            String summary = day.path("summary").asText();
            String icon = day.path("icon").asText();
            double temperature = day.path("temperature").asDouble();
            double humidity = day.path("humidity").asDouble();

            hourWeathers.add(new HourWeather(time, summary, icon, temperature, humidity));
        }

        FetchedData.hourWeathers = hourWeathers;
    }

    private static void doDaily(JsonNode root) {
        JsonNode dailyNode = root.path("daily");
        JsonNode dailyDataNode = dailyNode.path("data");
        getDailyWeather(dailyDataNode);
    }

    private static void getDailyWeather(JsonNode dailyNode) {
        Iterator<JsonNode> days = dailyNode.elements();
        ArrayList<DayWeather> dayWeathers = new ArrayList<>();

        while (days.hasNext()) {
            JsonNode day = days.next();

            long time = day.path("time").asLong();
            String summary = day.path("summary").asText();
            String icon = day.path("icon").asText();
            double temperature = day.path("temperatureHigh").asDouble();
            double humidity = day.path("humidity").asDouble();

            dayWeathers.add(new DayWeather(time, summary, icon, temperature, humidity));
        }

        FetchedData.dayWeathers = dayWeathers;
    }

    private static void delegateHours() {
        int dayIndex = 0;
        int hourIndex = 0;

        // Check to ensure that the indexes are contained in the lists of fetched data.
        while((dayIndex < FetchedData.dayWeathers.size()) && (hourIndex < FetchedData.hourWeathers.size())) {

            TimeAtMoment hourTime = new TimeAtMoment(FetchedData.hourWeathers.get(hourIndex).getTime());
            TimeAtMoment dayStartTime = new TimeAtMoment( FetchedData.dayWeathers.get(dayIndex).getTime());

            // First index of hour will ALWAYS be the same day you are in at index 0
            if(!hourTime.isSameDay(dayStartTime))
                dayIndex++;

            int indexOfHour = hourTime.getHour();
            FetchedData.dayWeathers.get(dayIndex).getHourlyWeather()[indexOfHour] = FetchedData.hourWeathers.get(hourIndex);
            hourIndex++;
        }
    }
}