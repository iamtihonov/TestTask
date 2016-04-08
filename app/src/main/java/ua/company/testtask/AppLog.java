package ua.company.testtask;
import android.util.Log;

public final class AppLog {
    public static final String TAG = "TestTask";

    public static void d(String msg) {
        Log.v(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }
}
