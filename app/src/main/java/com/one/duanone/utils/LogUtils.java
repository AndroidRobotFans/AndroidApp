package com.one.duanone.utils;

import android.util.Log;

/**
 * Created by Masterr_Robot on 2016/7/22.
 */
public class LogUtils {

    private static boolean show = true;

    public static void i(String TAG, String str) {
        if (show)
            Log.i(TAG, str);
    }

    public static void d(String TAG, String str) {
        if (show)
            Log.d(TAG, str);
    }

    public static void e(String TAG, String str) {
        if (show)
            Log.e(TAG, str);

    }

    public static void w(String TAG, String str) {
        if (show)
            Log.w(TAG, str);

    }


}
