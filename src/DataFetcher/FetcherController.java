package DataFetcher;

import DataStructures.*;
import Utility.HiddenConstants;
import Utility.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class FetcherController {
    private static String lat = "33.513833";
    private static String lon = "-117.103338";

    private static String url = HiddenConstants.WEB_SERVER + HiddenConstants.API_KEY + "/" + lat + "," + lon + HiddenConstants.ADDITIONAL_ARGS;

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

    private static void getCurrentWeather(JsonNode currentNode) {
        FetchedData.currentWeather = getWeatherByNode(currentNode, CurrentWeather.class);
    }

    private static void doHourly(JsonNode root) {
        JsonNode hourlyNode = root.path("hourly");
        JsonNode hourlyDataNode = hourlyNode.path("data");
        getHourlyWeather(hourlyDataNode);
    }

    @SuppressWarnings("unchecked")
    private static void getHourlyWeather(JsonNode hourlyNode) {
        FetchedData.hourWeathers = (ArrayList<HourWeather>) makeWeatherForNode(hourlyNode, HourWeather.class);
    }

    private static void doDaily(JsonNode root) {
        JsonNode dailyNode = root.path("daily");
        JsonNode dailyDataNode = dailyNode.path("data");
        getDailyWeather(dailyDataNode);
    }

    @SuppressWarnings("unchecked")
    private static void getDailyWeather(JsonNode dailyNode) {
        FetchedData.dayWeathers = (ArrayList<DayWeather>) makeWeatherForNode(dailyNode, DayWeather.class);
    }

    private static void delegateHours() {
        int dayIndex = 0;
        int hourIndex = 0;

        // Check to ensure that the indexes are contained in the lists of fetched data.
        while((dayIndex < FetchedData.dayWeathers.size()) && (hourIndex < FetchedData.hourWeathers.size())) {
            TimeAtMoment hourTime = new TimeAtMoment(Utils.getMillisFromEpoch(FetchedData.hourWeathers.get(hourIndex).getTime()));
            TimeAtMoment dayStartTime = new TimeAtMoment(Utils.getMillisFromEpoch(FetchedData.dayWeathers.get(dayIndex).getTime()));

            // First index of hour will ALWAYS be the same day you are in at index 0
            if(!hourTime.isSameDay(dayStartTime))
                dayIndex++;

            int indexOfHour = hourTime.getHour();
            FetchedData.dayWeathers.get(dayIndex).getHourlyWeather()[indexOfHour] = FetchedData.hourWeathers.get(hourIndex);
            hourIndex++;
        }
    }

    private static ArrayList<? extends WeatherParent> makeWeatherForNode(JsonNode weatherTypeNode, Class<? extends WeatherParent> clazz) {
        Iterator<JsonNode> nodes = weatherTypeNode.elements();
        ArrayList<WeatherParent> weatherParents = new ArrayList<>();

        while(nodes.hasNext()) {
            JsonNode node = nodes.next();
            weatherParents.add(getWeatherByNode(node, clazz));
        }

        return weatherParents;
    }

    @SuppressWarnings("unchecked")
    private static <T extends WeatherParent> T getWeatherByNode(JsonNode jsonNode, Class<? extends WeatherParent> clazz) {
        try {
            WeatherParent weatherType = clazz.newInstance();

            weatherType.setTime(jsonNode.path("time").asLong());
            weatherType.setSummary(jsonNode.path("summary").asText());
            weatherType.setIcon(jsonNode.path("icon").asText());
            weatherType.setTemperature(jsonNode.path(
                    weatherType instanceof DayWeather ? "temperatureHigh" : "temperature").asDouble());
            weatherType.setPrecipitationProbability(jsonNode.path("precipProbability").asDouble());
            weatherType.setHumidity(jsonNode.path("humidity").asDouble());
            weatherType.setWindSpeed(jsonNode.path("windSpeed").asInt());
            weatherType.setWindBearing(jsonNode.path("windBearing").asInt());

            return (T) weatherType;
        } catch (Exception e) {
            Utils.log("Could not parse Weather into type %s", clazz.getName());
            return null;
        }
    }
}
