package ua.company.testtask.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

import ua.company.testtask.Car;
import ua.company.testtask.ServiceClient;

public class MachinesLoader extends AsyncTaskLoader<ArrayList<Car>> {
    public MachinesLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Car> loadInBackground() {
        return ServiceClient.loadCars();
    }
}
