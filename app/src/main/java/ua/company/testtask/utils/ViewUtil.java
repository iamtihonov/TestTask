package ua.company.testtask.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.util.ArrayList;

public class ViewUtil {
    public static final String TEST_IMAGES_FOLDER_NAME = "test";

    public static int getDeviceWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static ArrayList<String> getTestImagesName() {
        File targetDirector = new File(getTestImagesFolderPath());

        File[] files = targetDirector.listFiles();
        ArrayList<String> imagesUri = new ArrayList<>();
        for (File file : files) {
            imagesUri.add(file.getName());
        }

        return imagesUri;
    }

    public static String getTestImagesFolderPath() {
        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        return ExternalStorageDirectoryPath + "/" + ViewUtil.TEST_IMAGES_FOLDER_NAME + "/";
    }
}
