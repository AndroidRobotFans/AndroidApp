package com.one.duanone.utils;

import com.one.duanone.bean.Pages;

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

    public static List<Pages> getForUrlPagerBean(String url) {
        List<Pages> list = new ArrayList<>();

        return list;
    }
    public static String getUrlString(String url){

        try {
            URL netPath = new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

}

