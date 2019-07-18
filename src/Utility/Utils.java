package Utility;

import DataFetcher.FetcherController;

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
            System.out.println("No internet connection!");
        }
    }

    public static boolean isEmptyJsonString(String string) {
        return (string == null || string.equalsIgnoreCase("") || string.equalsIgnoreCase("{}"));
    }

    public static boolean hasInternetConnection() {
        boolean status = false;
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
        try {
            socket.connect(address, 1500);
            if(socket.isConnected())
                status = true;
        } catch (Exception e) {
        } finally {
            try {
                socket.close();
            } catch (Exception e) {}
        }
        return status;
    }
}