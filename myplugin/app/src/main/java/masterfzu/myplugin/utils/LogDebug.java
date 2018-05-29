package masterfzu.myplugin.utils;

import android.util.Log;

import masterfzu.myplugin.BuildConfig;

/**
 * Created by zhengsiyuan on 2018-05-21.
 */

public class LogDebug {
    public static final String TAG = "RePlugin";

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
