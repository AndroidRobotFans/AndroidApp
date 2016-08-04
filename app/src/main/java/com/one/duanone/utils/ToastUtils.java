package com.one.duanone.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/1  20:22.
 * 统一管理类
 */
public class ToastUtils {

    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object synObj = new Object();


    private ToastUtils() {

    }

    public static void showToast(String str, Context context) {
        showMessage(context, str, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, int message) {
        showMessage(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * Toast发送消息
     *
     * @param act
     * @param msg
     * @param len
     */
    public static void showMessage(final Context act, final int msg, final int len) {
        Toast.makeText(act, msg, len).show();
    }

    public static void showMessage(final Context act, final String msg, final int len) {
        Toast.makeText(act, msg, len).show();
    }

    public static void cancleCurrentToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 在view的上面显示
     *
     * @param view
     * @param str
     * @param context
     */
    public static void showMoveToast(View view, String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
