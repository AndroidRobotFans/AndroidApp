package com.one.duanone.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/26  20:24.
 */
public class Utils {
    public static String openAssetsFile(Context context, String fileName) {

        InputStream inputStream = null;
        InputStreamReader reader = null;
        try {
            inputStream = context.getAssets().open(fileName);
            reader = new InputStreamReader(inputStream);
            char[] buffer = new char[256];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            while ((len = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
            String json = new String(sb);
            Log.i("TAG", "openAssetsFile: " + json);
            inputStream.close();
            reader.close();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                inputStream.close();
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public static Cursor getFileDb(String fileName) {

        File sd = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

        File dbFile = new File(sd, fileName);

        return null;
    }

    /**
     * 格式化为 2.2万这种
     *
     * @param count
     * @return
     */
    public static String formatCount(int count) {
        if (count >= 10000) {
            return count / 10000 + "." + count % 10 + "万";
        }
        return count + "";
    }

    /**
     * 格式化, 一秒为时间的,
     *
     * @param time
     * @return 00:00这种形式
     */
    public static String formatDurationS(int time) {

        int m = time / 60;
        int s = time % 60;
        String mm = m > 9 ? m + "" : "0" + m;
        mm = m == 0 ? 00 + "" : mm;
        String ss = s > 9 ? s + "" : "0" + s;
        return mm + ":" + ss;
    }

    /**
     * 将字符串写到文件中
     *
     * @param str
     */
    public static void outputFileString(String str) {
        int i = 0;
        String[] nams = {"推荐.txt"
                , "视频"
                , "图片"
                , "段子"
                , "直播"
                , "精华"
                , "同城"
                , "游戏"
        };
        String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String dirName = "NeiHan";
        File dir = new File(sdDir + "/" + dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, nams[i] + ".txt");
        if (file.exists()) {
            i++;
            file = new File(dir, nams[i] + ".txt");
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(str.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("TAG", "outputFileString: 写入成功" + nams[i] + dir.getAbsolutePath());
    }

    /**
     * 获取屏幕宽高
     * @param context
     * @return
     */
    public static int[] getWindowSize(Context context) {
        int w = 0;
        int h = 0;
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        w = (int) (displayMetrics.widthPixels / displayMetrics.density);
        h = (int) (displayMetrics.heightPixels / displayMetrics.density);
        return new int[]{w, h};
    }
}
