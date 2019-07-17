import Utility.Constants;
import Utility.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        String lat = "33.513833";
        String lon = "-117.103338";
        String additionalArgs = "?exclude=minutely,alerts,flags&extend=hourly";
        String url = Constants.WEB_SERVER + Constants.API_KEY + "/" + lat + "," + lon + additionalArgs;

        System.out.println("Using URL: " + url);

        try {
            URL darkSkyURL = new URL(url);
            URLConnection connection = darkSkyURL.openConnection();
            BufferedReader fetched = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String receivedData = fetched.readLine();

            if(Utils.isEmptyJsonString(receivedData))
                return;

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(receivedData);

            System.out.println(root);

            JsonNode currentNode = root.path("currently");

            JsonNode hourlyNode = root.path("hourly");
            JsonNode hourlyDataNode = hourlyNode.path("data");
            Iterator<JsonNode> hours = hourlyDataNode.elements();

            JsonNode dailyNode = root.path("daily");
            JsonNode dailyDataNode = dailyNode.path("data");
            Iterator<JsonNode> days = dailyDataNode.elements();

            //System.out.println(currentNode);
            //System.out.println(dailyDataNode);
            while (days.hasNext()) {
                JsonNode day = days.next();
                //System.out.println(day);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}