package ua.company.testtask.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import ua.company.testtask.data.Car;

public class ServiceClient {
    private static final int TIME_OUT_IN_MILL = 10000;
    private static final String GET_REQUEST_TAG = "GET";

    public static final String LOAD_CARS_URL = "https://www.dropbox.com/s/o18kagor849igu4/cars.json?dl=1&pv=1";

    public static String sendGetRequest(String urlToRead) {
        URL url;
        HttpsURLConnection connection;
        BufferedReader rd;
        String line;
        StringBuilder result = new StringBuilder();

        try {
            url = new URL(urlToRead);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(GET_REQUEST_TAG);
            connection.setConnectTimeout(TIME_OUT_IN_MILL);
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

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Car> loadCars() {
        String response = sendGetRequest(LOAD_CARS_URL);
        return response != null ? JsonParser.parseCars(response) : null;
    }
}
