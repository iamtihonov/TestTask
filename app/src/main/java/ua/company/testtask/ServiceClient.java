package ua.company.testtask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceClient {
    private static final int TIME_OUT = 1000;
    private static final int BUFFER_LENGTH_IN_BYTES = 1024;
    private static final String GET_REQUEST_TAG = "GET";

    public static final String LOAD_CARS_URL = "https://www.dropbox.com/s/6bcldgdhxa6mlk3/cars.json?dl=1&pv=1";

    public static String sendGetRequest(String urlToRead) {
        URL url;
        HttpURLConnection connection;
        BufferedReader rd;
        String line;
        StringBuilder result = new StringBuilder();

        try {
            url = new URL(urlToRead);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET_REQUEST_TAG);
            connection.setConnectTimeout(TIME_OUT);
            int statusCode = connection.getResponseCode();
            switch (statusCode) {
                case 200:
                case 201:
                    rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    rd.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
