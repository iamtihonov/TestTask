package ua.company.testtask.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ua.company.testtask.AppLog;
import ua.company.testtask.Car;

public class MachinesLoader extends AsyncTaskLoader<ArrayList<Car>> {
    public MachinesLoader(Context context) {
        super(context);
        AppLog.d("MachinesLoader()");
    }

    @Override
    public ArrayList<Car> loadInBackground() {
        AppLog.d("MachinesLoader, loadInBackground = start");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AppLog.d("MachinesLoader, loadInBackground = finish");
        return new ArrayList<>();
    }
}
