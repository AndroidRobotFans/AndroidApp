package com.one.duanone.utils;

import com.one.duanone.bean.Pages;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  22:24.
 */
public class NetUtils {

    private static final String TAG = NetUtils.class.getSimpleName();
    private static int OUT_TIME = 8000;

    public static List<Pages> getForUrlPagerBean(String url, NetCallBack callBack) {
        List<Pages> list = new ArrayList<>();
        String jsonStr = getUrlString(url, callBack);
        list = JsonUtils.getJsonBean(jsonStr, callBack);
        //请求结果回调
        if (list != null) {
            callBack.succeed();
        }else {
            callBack.error("解析失败");
        }
        return list;
    }

    /**
     * 通过URL获取到里面的String数据
     *
     * @param url
     * @param callBack
     * @return
     */
    public static String getUrlString(String url, NetCallBack callBack) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            URL netPath = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) netPath.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(OUT_TIME);
            connection.setConnectTimeout(OUT_TIME);
            if (connection.getResponseCode() != 200) {
                LogUtils.i(TAG, "HTTP请求返回失败");
                error(callBack, "HTTP请求Code失败");
                return null;
            }
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            StringBuffer sb = new StringBuffer();

            char[] buffer = new char[512];
            int len = 0;
            while ((len = inputStreamReader.read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
            return new String(sb.toString().getBytes(), "utf-8");
        } catch (MalformedURLException e) {
            error(callBack, e.getMessage());
        } catch (IOException e) {
            error(callBack, e.getMessage());
        }
        return null;
    }

    /**
     * 请求网络的接口回调
     */
    public interface NetCallBack {
        /**
         * 请求成功回调
         */
        void succeed();

        /**
         * 请求失败回调
         *
         * @param e 错误信息
         */
        void error(String e);
    }

    private static void error(NetCallBack callBack, String e) {
        if (callBack != null) {
            callBack.error(e);
        }
    }

    private static void succeed(NetCallBack callBack) {
        if (callBack != null) {
            callBack.succeed();
        }
    }

}

