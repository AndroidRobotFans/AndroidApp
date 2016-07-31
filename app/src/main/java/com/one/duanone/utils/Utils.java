package com.one.duanone.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import java.io.File;
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
}
