package ua.company.testtask.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

import ua.company.testtask.data.Car;
import ua.company.testtask.handlers.ServiceClient;

public class CarsLoader extends AsyncTaskLoader<ArrayList<Car>> {
    public CarsLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Car> loadInBackground() {
        return ServiceClient.loadCars();
    }
}
