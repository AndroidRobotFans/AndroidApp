package com.one.duanone.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/9  10:00.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String JOKE_TABLE_NAME = "joke_item";
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
            "category_name text)";

    public DBHelper(Context context, String name, int version) {
        this(context, name, null, version);

    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
