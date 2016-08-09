package com.one.duanone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.one.duanone.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/9  10:31.
 */
public class JokeDatabase {

    private static final String JOKE_TABLE_NAME = "joke_item";

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static final String name = "joke.db";
    private static final int version = 1;
    private static String tabName;

    private static final String CREATE_TABLE = "create table " + JOKE_TABLE_NAME + " (" +
            "id integer primary key autoincrement," +
            "item_id integer, " +
            "category_id integer, " +
            "behot_time integer, " +
            "share_url text, " +
            "digg_count integer, " +
            "bury_count integer, " +
            "repin_count integer, " +
            "share_count integer, " +
            "comment_count integer, " +
            "content text, " +
            "user text, " +
            "large_image text, " +
            "middle_image text, " +
            "last_use_time integer, " +
            "video_360p text, " +
            "video_720p text, " +
            "god_comments text, " +
            "isLive integer, " +
            "live text, " +
            "category_name text)";

    private static JokeDatabase database;

    private JokeDatabase(Context context) {
        dbHelper = new DBHelper(context, name, version, CREATE_TABLE);
    }

    public static JokeDatabase getInstance(Context context) {
        if (database == null) {
            database = new JokeDatabase(context);
        }
        return database;
    }

    public void insert(String key, Object value) {

    }


    /**
     * mediaType 的值
     * 0	文字
     * 1	只有图片时图片
     * 2	gif图
     * 3	视频
     * 4	图片
     *
     * @param news
     * @param userJson
     * @param mediaJson
     */
    public void insertAll(List<News> news, String userJson, String mediaJson) {
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < news.size(); i++) {
            int isLive = 0;
            //是直播
            if (news.get(i).isLive()) {
                isLive = 1;
                contentValues.put("live", mediaJson);
            }
            int mediaType = news.get(i).getGroup().getMedia_type();
            String mediaKey = "";

        }


    }

}
