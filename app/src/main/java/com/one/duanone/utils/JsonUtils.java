package com.one.duanone.utils;

import com.one.duanone.bean.Pages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  23:06.
 */
public class JsonUtils {

    /**
     * 通过json解析出每个页面的title和对应的网址
     *
     * @param jsonStr
     * @param callBack 监听回调
     * @return
     */
    public static List<Pages> getJsonBean(String jsonStr, NetUtils.NetCallBack callBack) {
        List<Pages> list = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(jsonStr);
            JSONArray data = obj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                Pages pages = new Pages();
                pages.setPageUrl(object.getString("url"));
                pages.setPageTitle("name");
                list.add(pages);
            }
            return list;
        } catch (JSONException e) {
            error(callBack, e.getMessage());
        }
        return null;
    }

    private static void error(NetUtils.NetCallBack callBack, String e) {
        if (callBack != null) {
            callBack.error(e);
        }
    }

    private static void succeed(NetUtils.NetCallBack callBack) {
        if (callBack != null) {
            callBack.succeed();
        }
    }
}
