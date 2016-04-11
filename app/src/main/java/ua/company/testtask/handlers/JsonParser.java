package ua.company.testtask.handlers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ua.company.testtask.data.Car;

public class JsonParser {
    private static final String CARS_TAG = "cars";

    private static final String MODEL_NAME_TAG = "model";
    private static final String YEAR_TAG = "year";
    private static final String COLOR_TAG = "color";
    private static final String PRICE_TAG = "price";

    public static ArrayList<Car> parseCars(String response) {
        ArrayList<Car> cars = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray jsonCars = jsonResponse.getJSONArray(CARS_TAG);

            int length = jsonCars.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonCar = jsonCars.getJSONObject(i);

                String modelName = jsonCar.getString(MODEL_NAME_TAG);
                int year = jsonCar.getInt(YEAR_TAG);
                String color = jsonCar.getString(COLOR_TAG);
                int price = jsonCar.getInt(PRICE_TAG);
                cars.add(new Car(modelName, year, color, price));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cars;
    }
}
