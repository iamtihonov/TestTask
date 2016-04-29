package ua.company.testtask.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetUtil {
    public static boolean deviceIsOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null) {
            return false;
        } else if (!i.isConnected()) {
            return false;
        } else if (!i.isAvailable()) {
            return false;
        }

        return true;
    }
}
