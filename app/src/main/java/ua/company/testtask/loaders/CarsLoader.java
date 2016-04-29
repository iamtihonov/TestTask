package ua.company.testtask.loaders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

import ua.company.testtask.data.Car;
import ua.company.testtask.handlers.ServiceClient;
import ua.company.testtask.utils.InternetUtil;

public class CarsLoader extends AsyncTaskLoader<ArrayList<Car>> {
    private ArrayList<Car> mLoadedCars;
    private BroadcastReceiver mNetworkStateReceiver;
    public CarsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mLoadedCars != null) {
            deliverResult(mLoadedCars);
        }

        if (takeContentChanged() || mLoadedCars == null) {
            forceLoad();
        }
    }

    @Override
    public ArrayList<Car> loadInBackground() {
        mLoadedCars = ServiceClient.loadCars();
        if(mLoadedCars == null) {
            mNetworkStateReceiver = new NetworkStateReceiver();
            getContext().registerReceiver(mNetworkStateReceiver, new IntentFilter(
                    ConnectivityManager.CONNECTIVITY_ACTION));
        } else {
            unregisterReceiverIfNeed();
        }

        return mLoadedCars;
    }

    @Override
    public void deliverResult(ArrayList<Car> loadedCars) {
        if (isReset()) {
            return;
        }

        if (isStarted()) {
            super.deliverResult(loadedCars);
        }
    }

    public class NetworkStateReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if(InternetUtil.deviceIsOnline(context)) {
                onContentChanged();
            }
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if (mLoadedCars != null) {
            mLoadedCars = null;
        }

        unregisterReceiverIfNeed();
    }

    public void unregisterReceiverIfNeed() {
        if(mNetworkStateReceiver != null) {
            getContext().unregisterReceiver(mNetworkStateReceiver);
            mNetworkStateReceiver = null;
        }
    }
}
