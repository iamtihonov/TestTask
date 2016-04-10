package ua.company.testtask.debug;
import android.util.Log;

public final class AppLog {
    public static final String TAG = "TestTask";

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }
}
