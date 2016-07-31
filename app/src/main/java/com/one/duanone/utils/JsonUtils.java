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
            } else {
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
            Log.i(TAG, "getJsonNews:========== "+json);
            JSONObject JSON = new JSONObject(json);
            //有这个属性的json是直播类型的
            if (JSON.has("status_code")) {
                list = formatLive(JSON);
                Log.i(TAG, "getJsonNews:视频类型 status_code");

                return list;
            }

            JSONObject jsonData = JSON.getJSONObject("data");
            JSONArray dataArray = jsonData.getJSONArray("data");


            for (int i = 0; i < dataArray.length(); i++) {
                News news = new News();
                String message = JSON.optString("message");
                news.setMessage(message);

                String tip = jsonData.optString("tip");
                news.setTip(tip);
                JSONObject obj = dataArray.getJSONObject(i);
                //不包含group的直接返回
                if (!obj.has("group")) {
                    Log.i(TAG, "getJsonNews: 没有Group");
                    continue;
                }
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
                String content = groupObj.optString("content");
                group.setContent(content);

                JSONObject userObj = groupObj.getJSONObject("user");
                News.User user = getUser(userObj);
                //解析用户
                if (user != null && user instanceof News.User) {
                    group.setUser(user);
                    Log.i(TAG, "getJsonNews: 解析用户");
                }
                //解析图片
                if (media_type == 1 || media_type == 2 || media_type == 4) {
                    News.ImageNew imageNew = formatImage(groupObj);
                    if (imageNew instanceof News.ImageNew) {
                        group.setImageNew(imageNew);
                        Log.i(TAG, "getJsonNews: 解析图片");
                    }
                }
                //解析视频
                if (media_type == 3) {
                    News.OriginVideo videoNew = formatVideo(groupObj);
                    if (videoNew instanceof News.OriginVideo) {
                        group.setOriginVideo(videoNew);
                        Log.i(TAG, "getJsonNews: 解析视频");
                    }
                }
                news.setMessage("success");
                news.setGroup(group);
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 解析视频直播的方法
     *
     * @param jsonObj
     * @return
     */
    private static List<News> formatLive(JSONObject jsonObj) {
        List<News> list = new ArrayList<>();
        JSONArray dataArray = jsonObj.optJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++) {
            News news = new News();

            News.LiveNew liveNew = new News.LiveNew();
            News.User user = new News.User();
            JSONObject object = dataArray.optJSONObject(i);
            JSONObject data = object.optJSONObject("data");

            JSONObject owner = data.optJSONObject("owner");

            JSONObject avatar_large = owner.optJSONObject("avatar_large");

            JSONArray url_list = avatar_large.optJSONArray("url_list");

            JSONObject avatar_thumb = owner.optJSONObject("avatar_thumb");
            JSONArray url_list1 = avatar_thumb.optJSONArray("url_list");
            //头像
            String iconUrl = url_list1.optString(0);

            //解析用户的属性
            int userId = owner.optInt("id");
            String nickname = owner.optString("nickname");
            user.setName(nickname);
            user.setUser_id(userId);
            user.setAvatar_url(iconUrl);

            liveNew.setUser(user);

            JSONObject stream_url = data.optJSONObject("stream_url");

            int id = stream_url.optInt("id");
            String rtmp_pull_url = stream_url.optString("rtmp_pull_url");
            String city = owner.optString("city");
            String birthday_description = owner.optString("birthday_description");
            String constellation = owner.optString("constellation");
            //直播间在线人数
            int user_count = data.optInt("user_count");
            //直播封面
            String url = url_list.optString(0);
            liveNew.setImgUrl(url);
            liveNew.setId(id);
            liveNew.setUser_count(user_count);
            liveNew.setCity(city);
            liveNew.setBirthday_description(birthday_description);
            liveNew.setLiveUrl(rtmp_pull_url);
            liveNew.setConstellation(constellation);

            news.setLiveNew(liveNew);
            //把类型设置为直播类型
            news.setIsLive(true);
            list.add(news);

        }
        return list;
    }

    /**
     * 解析图片类型
     *
     * @param jsonObj
     * @return
     */
    private static News.ImageNew formatImage(JSONObject jsonObj) {
        JSONObject large_image = jsonObj.optJSONObject("large_image");
        News.ImageNew imageNew = new News.ImageNew();
        if (large_image instanceof JSONObject) {
            //是JsonObject的时候才执行
            JSONArray url_list = large_image.optJSONArray("url_list");
            String url = url_list.optJSONObject(0).optString("url");
            imageNew.setImgUrl(url);
            return imageNew;
        }
        JSONObject middle_image = jsonObj.optJSONObject("middle_image");
        if (middle_image instanceof JSONObject) {
            JSONArray url_list = middle_image.optJSONArray("url_list");
            String url = url_list.optJSONObject(0).optString("url");
            imageNew.setImgUrl(url);
            return imageNew;
        }
        return null;
    }

    /**
     * 解析视频类型
     *
     * @param jsonObj
     * @return
     */
    private static News.OriginVideo formatVideo(JSONObject jsonObj) {
        News.OriginVideo originVideo = new News.OriginVideo();
        int play_count = jsonObj.optInt("play_count");
        int duration = (int) jsonObj.optDouble("duration");
        JSONObject medium_cover = jsonObj.optJSONObject("medium_cover");
        JSONArray imgArray = medium_cover.optJSONArray("url_list");
        String imageUrl = null;
        //是否循环完数组,确认每个对象中没有图片地址后,在结束
        for (int i = 0; i < imgArray.length(); i++) {
            if (imageUrl != null) continue;
            imageUrl = imgArray.optJSONObject(i).optString("url");

        }

        JSONObject videoArray = jsonObj.optJSONObject("720p_video");

        JSONArray vArray = videoArray.optJSONArray("url_list");
        String videoUrl = null;

        for (int j = 0; j < vArray.length(); j++) {
            if (videoUrl != null) continue;
            videoUrl = vArray.optJSONObject(j).optString("url");
        }
        originVideo.setDuration(duration);
        originVideo.setPlay_count(play_count);
        originVideo.setMedium_cover_url(imageUrl);
        originVideo.setOrigin_video_url(videoUrl);

        return originVideo;
    }

    /**
     * 解析 用户
     *
     * @param userObj
     * @return
     */
    public static News.User getUser(JSONObject userObj) {
        News.User user = new News.User();

        if (userObj instanceof JSONObject) {
            //说明有user这个对象, 不是匿名用户
            String avatar_url = userObj.optString("avatar_url");
            String name = userObj.optString("name");
            int user_id = userObj.optInt("user_id");

            user.setAvatar_url(avatar_url);
            user.setName(name);
            user.setUser_id(user_id);

            return user;
        }

        //获取值的时候, 根据null 和-1 来判断是否匿名用户
        String avatar_url = "";
        String name = "匿名";
        int user_id = -1;

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
