package com.one.duanone.utils;

import android.util.Log;

import com.one.duanone.bean.News;
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

    private static final String TAG = JsonUtils.class.getSimpleName();

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
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray data = jsonObj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                Pages pages = new Pages();
                String url = object.getString("url");
                String name = object.getString("name");
                pages.setPageTitle(name);
                pages.setPageUrl(url);
                list.add(pages);
                Log.i(TAG, "getJsonBean: " + pages.toString());
            }
            if (list != null) {
                callBack.succeed();
            }
            else {
                callBack.error("list is not!!");
            }
            Log.i(TAG, "getJsonBean: " + list.size());
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<News> getJsonNews(String json) {

        try {
            List<News> list = new ArrayList<>();

            JSONObject JSON = new JSONObject(json);

            JSONObject jsonData = JSON.getJSONObject("data");
            JSONArray dataArry = jsonData.getJSONArray("data");

            for (int i = 0; i < dataArry.length(); i++) {
                News news = new News();
                String message = JSON.optString("message");
                news.setMessage(message);

                String tip = jsonData.optString("tip");
                news.setTip(tip);
                JSONObject obj = dataArry.getJSONObject(i);

                JSONObject groupObj = obj.getJSONObject("group");

                News.Group group = new News.Group();

                int share_count = groupObj.optInt("share_count");
                group.setShare_count(share_count);

                int bury_count = groupObj.getInt("bury_count");
                group.setBury_count(bury_count);

                int category_id = groupObj.optInt("category_id");
                group.setCategory_id(category_id);

                String category_name = groupObj.optString("category_name");
                group.setCategory_name(category_name);

                int digg_count = groupObj.optInt("digg_count");
                group.setDigg_count(digg_count);

                int comment_count = groupObj.optInt("comment_count");
                group.setComment_count(comment_count);

                int favorite_count = groupObj.optInt("favorite_count");
                group.setFavorite_count(favorite_count);

                int go_detail_count = groupObj.optInt("go_detail_count");
                group.setGo_detail_count(go_detail_count);

                int group_id = groupObj.optInt("group_id");
                group.setGroup_id(group_id);

                String share_url = groupObj.optString("share_url");
                group.setShare_url(share_url);

                int media_type = groupObj.optInt("media_type");
                group.setMedia_type(media_type);

                JSONObject userObj = groupObj.getJSONObject("user");
                if (media_type == 1 || media_type == 2 || media_type == 4){
                    News.User user = getUser(userObj);
                    if (user != null) {
                        group.setUser(user);
                    }
                }
                if (media_type ==3){



                }




            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * 解析 用户
     * @param userObj
     * @return
     */
    public static News.User getUser(JSONObject userObj) {

        News.User user  = new News.User();

        String avatar_url = userObj.optString("avatar_url");
        String name = userObj.optString("name");
        int user_id = userObj.optInt("user_id");

        user.setAvatar_url(avatar_url);
        user.setName(name);
        user.setUser_id(user_id);

        return user;
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
