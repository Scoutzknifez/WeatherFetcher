package Utility;

import DataFetcher.FetcherController;
import DataStructures.TimeAtMoment;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Utils {
    public static long getMillisFromEpoch(long epoch) {
        return epoch * Constants.MILLIS_IN_SECOND;
    }

    public static void initializeApplication() {
        Constants.hasInternet = hasInternetConnection();
        if(Constants.hasInternet) {
            FetcherController.fetchWeather();
        } else {
            Utils.log("No internet connection!");
        }
    }

    /**
     * Sends a message out to console with time stamp of log execution
     *
     * NOTE: use %s to replace part of string with object
     *
     * @param message   message to display with replaceable characters for objects
     * @param objects   Objects to replace inside of the message string
     */
    public static void log(String message, Object... objects) {
        TimeAtMoment timeAtMoment = new TimeAtMoment(System.currentTimeMillis());

        System.out.println("[" + timeAtMoment + "] " + String.format(message, objects));
    }

    public static boolean isEmptyJsonString(String string) {
        return (string == null || string.equalsIgnoreCase("") || string.equalsIgnoreCase("{}"));
    }

    public static boolean hasInternetConnection() {
        boolean status = false;
        try (Socket socket = new Socket()) {
            InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
            socket.connect(address, 1500);
            if (socket.isConnected())
                status = true;
        } catch (Exception e) {
            Utils.log("No internet connection available!");
        }
        return status;
    }
}
